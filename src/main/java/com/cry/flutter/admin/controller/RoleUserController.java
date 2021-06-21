package com.cry.flutter.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.RoleUser;
import com.cry.flutter.admin.service.IRoleUserService;
import com.cry.flutter.admin.service.IUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-08-20
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/roleUser")
public class RoleUserController {

    @Resource(name = "roleUserService")
    IRoleUserService roleUserService;
    @Resource(name = "userService")
    IUserService userService;


    @PostMapping(value = "saveBatch", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBodyApi saveBatch(@RequestBody List<RoleUser> roleUserList) {
        roleUserService.saveBatch(roleUserList);
        return new ResponseBodyApi();
    }


    @PostMapping("removeBatch")
    public ResponseBodyApi removeBatch(@RequestBody List<RoleUser> roleUserList) {
        roleUserList.forEach(v -> roleUserService.remove(new QueryWrapper<RoleUser>().lambda().eq(RoleUser::getUserId, v.getUserId()).eq(RoleUser::getRoleId, v.getRoleId())));
        return new ResponseBodyApi();
    }

    @PostMapping("removeByIds")
    public ResponseBodyApi removeByIds(@RequestBody List<String> idList) {
        roleUserService.removeByIds(idList);
        return new ResponseBodyApi();
    }
}
