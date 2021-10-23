package com.cry.flutter.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cry.flutter.admin.common.EnvUtil;
import com.cry.flutter.admin.common.Operation;
import com.cry.flutter.admin.common.RedisUtil;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.constants.Constant;
import com.cry.flutter.admin.entity.User;
import com.cry.flutter.admin.entity.UserInfo;
import com.cry.flutter.admin.service.IUserInfoService;
import com.cry.flutter.admin.service.IUserService;
import com.cry.flutter.admin.utils.JwtUtil;
import com.cry.flutter.admin.utils.RequestUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource(name = "userService")
    IUserService userService;

    @Resource(name = "userInfoService")
    IUserInfoService userInfoService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private EnvUtil envUtil;

    @ApiOperation(value = "注册")
    @ApiResponses({@ApiResponse(code = 200, message = "OK", response = ResponseBodyApi.class)})
    @Operation()
    @PostMapping("register")
    public ResponseBodyApi register(@RequestBody User user) throws Exception {
        List<User> userList = userService.list(new QueryWrapper<User>().lambda().eq(User::getUserName, user.getUserName()));
        if (!userList.isEmpty()) {
            return new ResponseBodyApi(false, "此账号已存在");
        }
        userService.save(user);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setUserName(user.getUserName());
        userInfoService.save(userInfo);

        return new ResponseBodyApi();
    }

    @Operation()
    @PostMapping("login")
    public ResponseBodyApi login(@RequestBody User user) throws Exception {
        List<User> list = userService.list(new QueryWrapper<User>().lambda().eq(User::getUserName, user.getUserName()).eq(User::getPassword, user.getPassword()));
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseBodyApi(false, "账号或密码错误");
        } else {
            User existUser = list.get(0);
            String token = JwtUtil.createJWT(existUser.getId());
            RequestUtil.getRequest().setAttribute("userId", existUser.getId());
            redisUtil.setEx(Constant.REDIS_TOKEN_PRE + existUser.getId(), token, envUtil.getTokenTimeout(), TimeUnit.SECONDS);

            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("currentUserInfo", userInfoService.getCurrentUserInfo());

            return new ResponseBodyApi(map);
        }
    }


}
