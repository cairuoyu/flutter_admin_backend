package com.cry.flutter.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cry.flutter.admin.common.Operation;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.Dept;
import com.cry.flutter.admin.entity.User;
import com.cry.flutter.admin.entity.UserInfo;
import com.cry.flutter.admin.service.IDeptService;
import com.cry.flutter.admin.service.IUserInfoService;
import com.cry.flutter.admin.service.IUserService;
import com.cry.flutter.admin.utils.RequestUtil;
import com.cry.flutter.admin.wrapper.UserInofWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
@RequestMapping(value = "/userInfo")
public class UserInfoController {

    @Resource(name = "userService")
    IUserService userService;

    @Resource(name = "userInfoService")
    IUserInfoService userInfoService;

    @Resource(name = "deptServiceImpl")
    IDeptService deptService;

    @Operation()
    @PostMapping("list")
    public ResponseBodyApi<List<UserInfo>> list(@RequestBody RequestBodyApi<UserInfo> requestBodyApi) {
        return new ResponseBodyApi<>(userInfoService.list(getQueryWrapper(requestBodyApi)));
    }

    private LambdaQueryWrapper<UserInfo> getQueryWrapper(RequestBodyApi<UserInfo> requestBodyApi) {
        UserInfo userInfo = requestBodyApi.getParams();
        LambdaQueryWrapper<UserInfo> queryWrapper = new QueryWrapper<UserInfo>().lambda();
        if (userInfo != null) {
            queryWrapper = queryWrapper.like(!StringUtils.isEmpty(userInfo.getName()), UserInfo::getName, userInfo.getName())
                    .eq(!StringUtils.isEmpty(userInfo.getDeptId()), UserInfo::getDeptId, userInfo.getDeptId())
                    .eq(!StringUtils.isEmpty(userInfo.getGender()), UserInfo::getGender, userInfo.getGender());
        }
        return queryWrapper;
    }

    @PostMapping("page")
    public ResponseBodyApi<IPage<UserInofWrapper>> page(@RequestBody RequestBodyApi<UserInfo> requestBodyApi) {
        IPage<UserInofWrapper> page = userInfoService.page(requestBodyApi);
        return new ResponseBodyApi<>(page);
    }


//    @PostMapping("getCurrentUserInfo")
//    public ResponseBodyApi getCurrentUserInfo() {
//        UserInfo userInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getUserId, RequestUtil.getCurrentUserId()));
//        Dept dept = deptService.getOne(new QueryWrapper<Dept>().lambda().eq(Dept::getId, userInfo.getDeptId()));
//
//        UserInofWrapper userInofWrapper = new UserInofWrapper();
//        BeanUtils.copyProperties(userInfo, userInofWrapper);
//        if (dept != null) {
//            userInofWrapper.setDeptName(dept.getName());
//        }
//        return new ResponseBodyApi<UserInofWrapper>(userInofWrapper);
//    }

    @PostMapping("getById")
    public ResponseBodyApi getById(@RequestBody UserInfo userInfo) {
        return new ResponseBodyApi<UserInfo>(userInfoService.getById(userInfo.getId()));
    }

    @PostMapping("saveOrUpdate")
    public ResponseBodyApi saveOrUpdate(@RequestBody UserInfo userInfo) {
        if (userInfo.getId() == null) {
            if (StringUtils.isEmpty(userInfo.getUserName())) {
                return new ResponseBodyApi(false, "账号不能为空");
            }
            List<User> userList = userService.list(new QueryWrapper<User>().lambda().eq(User::getUserName, userInfo.getUserName()).ne(!StringUtils.isEmpty(userInfo.getUserId()), User::getId, userInfo.getUserId()));
            if (!userList.isEmpty()) {
                return new ResponseBodyApi(false, "此账号已存在");
            }
            User user = new User();
            user.setPassword("111111");
            user.setUserName(userInfo.getUserName());
            userService.save(user);
            userInfo.setUserId(user.getId());
        }
        userInfoService.saveOrUpdate(userInfo);
        return new ResponseBodyApi();
    }

    @PostMapping("removeByIds")
    public ResponseBodyApi removeByIds(@RequestBody List<String> idList) {
//        userInfoService.removeByIds(idList);
        return new ResponseBodyApi();
    }


}
