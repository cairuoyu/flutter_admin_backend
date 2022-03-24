package com.cry.flutter.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cry.flutter.admin.common.*;
import com.cry.flutter.admin.constants.Constant;
import com.cry.flutter.admin.entity.SettingDefaultTab;
import com.cry.flutter.admin.entity.User;
import com.cry.flutter.admin.entity.UserInfo;
import com.cry.flutter.admin.service.*;
import com.cry.flutter.admin.utils.FileUtil;
import com.cry.flutter.admin.utils.JwtUtil;
import com.cry.flutter.admin.utils.RequestUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cairuoyu
 * @since 2021-01-13
 */
@Slf4j
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private FileProperties fileProperties;

    @Resource(name = "userService")
    IUserService userService;

    @Resource(name = "userInfoService")
    IUserInfoService userInfoService;

    @Resource(name = "settingDefaultTabServiceImpl")
    ISettingDefaultTabService settingDefaultTabService;

    @Resource(name = "fileServiceImpl")
    IFileService fileService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private EnvUtil envUtil;

    @ApiOperation(value = "注册")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = ResponseBodyApi.class)})
    @Operation()
    @PostMapping("register")
    public ResponseBodyApi register(@RequestParam(value = "file", required = false) MultipartFile mf, User user) throws Exception {
        List<User> userList = userService.list(new QueryWrapper<User>().lambda().eq(User::getUserName, user.getUserName()));
        if (!userList.isEmpty()) {
            return new ResponseBodyApi(false, "此账号已存在");
        }
        userService.save(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setUserName(user.getUserName());
        userInfoService.save(userInfo);

        if (mf != null) {
            String filename = StringUtils.cleanPath(mf.getOriginalFilename());
            filename = FileUtil.codeFileName(filename, "png");
            userInfo.setAvatarUrl(fileProperties.getDownloadPath() + filename);
            fileService.storeFile(mf, userInfo.getId(), filename);
            userInfoService.updateById(userInfo);
        }

        SettingDefaultTab settingDefaultTab = new SettingDefaultTab();
        settingDefaultTab.setUserId(user.getId());
        settingDefaultTab.setName("Dashboard");
        settingDefaultTab.setNameEn("Dashboard");
        settingDefaultTab.setUrl("/dashboard");
        settingDefaultTabService.save(settingDefaultTab);

        return new ResponseBodyApi();
    }

    @Operation()
    @PostMapping("login")
    public ResponseBodyApi login(@RequestBody User user) throws Exception {
//        String password = DigestUtils.md5DigestAsHex((user.getPassword() + "slat").getBytes());
        List<User> list = userService.list(new QueryWrapper<User>().lambda().eq(User::getUserName, user.getUserName()).eq(User::getPassword, user.getPassword()));
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseBodyApi(false, "账号或密码错误");
        } else {
            return new ResponseBodyApi(loginSuccess(list.get(0)));
        }
    }

    @Operation()
    @PostMapping("loginByFace")
    public ResponseBodyApi loginByFace(@RequestBody String face) throws Exception {
        String[] face1 = face.split(",");
        List<User> userList = userService.list(new QueryWrapper<User>().lambda().isNotNull(User::getFace));
        for (User user : userList) {
            String[] face2 = user.getFace().split(",");
            double e = Utils.euclideanDistance(face1, face2);
            log.info("--face:{}-{}", user.getUserName(), e);
            if (e < 1) {
                return new ResponseBodyApi(loginSuccess(user));
            }
        }
        return new ResponseBodyApi(false, "你还未注册人脸，请先注册");
    }

    Map<String, Object> loginSuccess(User user) {
        String token = JwtUtil.createJWT(user.getId());
        RequestUtil.getRequest().setAttribute("userId", user.getId());
        redisUtil.setEx(Constant.REDIS_TOKEN_PRE + user.getId(), token, envUtil.getTokenTimeout(), TimeUnit.SECONDS);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("currentUserInfo", userInfoService.getCurrentUserInfo());
        return map;
    }

}
