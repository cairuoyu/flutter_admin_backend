package com.cry.flutter.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cry.flutter.admin.common.Operation;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.Role;
import com.cry.flutter.admin.entity.UserInfo;
import com.cry.flutter.admin.service.IRoleService;
import com.cry.flutter.admin.vo.MenuVO;
import com.cry.flutter.admin.vo.RoleMenuVO;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
@RequestMapping(value = "/role")
public class RoleController {

    @Resource(name = "roleService")
    IRoleService roleService;


    @PostMapping("getMenu")
    public ResponseBodyApi<List<MenuVO>> getMenu(@RequestBody RequestBodyApi<RoleMenuVO> requestBodyApi) {
        List<MenuVO> list = roleService.getMenu(requestBodyApi.getParams());
        return new ResponseBodyApi<>(list);
    }

//    @PostMapping("getUnSelectedMenu")
//    public ResponseBodyApi<Page<Menu>> getUnSelectedMenu(@RequestBody RequestBodyApi<Role> requestBodyApi) {
//        Page page = roleService.getUnSelectedMenu(requestBodyApi.getPage(), requestBodyApi.getParams());
//        return new ResponseBodyApi<Page<Menu>>(page);
//    }
//
//    @PostMapping("getSelectedMenu")
//    public ResponseBodyApi<Page<Menu>> getSelectedMenu(@RequestBody RequestBodyApi<Role> requestBodyApi) {
//        Page page = roleService.getSelectedMenu(requestBodyApi.getPage(), requestBodyApi.getParams());
//        return new ResponseBodyApi<Page<Menu>>(page);
//    }

    @PostMapping("getUnSelectedUserInfo")
    public ResponseBodyApi<Page<UserInfo>> getUnSelectedUserInfo(@RequestBody RequestBodyApi<Map<String, Object>> requestBodyApi) {
        Page page = roleService.getUnSelectedUserInfo(requestBodyApi.getPage(), requestBodyApi.getParams());
        return new ResponseBodyApi<Page<UserInfo>>(page);
    }

    @PostMapping("getSelectedUserInfo")
    public ResponseBodyApi<Page<UserInfo>> getSelectedUserInfo(@RequestBody RequestBodyApi<Map<String, Object>> requestBodyApi) {
        Page page = roleService.getSelectedUserInfo(requestBodyApi.getPage(), requestBodyApi.getParams());
        return new ResponseBodyApi<Page<UserInfo>>(page);
    }


    @Operation()
    @PostMapping("list")
    public ResponseBodyApi<List<Role>> list(@RequestBody RequestBodyApi<Role> requestBodyApi) {
        return new ResponseBodyApi<>(roleService.list(getQueryWrapper(requestBodyApi)));
    }

    private LambdaQueryWrapper<Role> getQueryWrapper(RequestBodyApi<Role> requestBodyApi) {
        Role role = requestBodyApi.getParams();
        LambdaQueryWrapper<Role> queryWrapper = new QueryWrapper<Role>().lambda();
        if (role != null) {
            queryWrapper = queryWrapper.like(!StringUtils.isEmpty(role.getName()), Role::getName, role.getName());
        }
        return queryWrapper;
    }

    @PostMapping("page")
    public ResponseBodyApi<Page<Role>> page(@RequestBody RequestBodyApi<Role> requestBodyApi) {
        Page<Role> page = roleService.page(requestBodyApi.getPage(), getQueryWrapper(requestBodyApi));
        return new ResponseBodyApi<Page<Role>>(page);
    }

    @PostMapping("getById")
    public ResponseBodyApi getById(@RequestBody Role role) {
        return new ResponseBodyApi<Role>(roleService.getById(role.getId()));
    }

    @PostMapping("saveOrUpdate")
    public ResponseBodyApi saveOrUpdate(@RequestBody Role role) {
        int count = roleService.count(new QueryWrapper<Role>().lambda().eq(Role::getName, role.getName()).ne(!StringUtils.isEmpty(role.getId()), Role::getId, role.getId()));
        if (count > 0) {
            return new ResponseBodyApi(false, "此角色名称已存在");
        }
        roleService.saveOrUpdate(role);
        return new ResponseBodyApi();
    }

    @PostMapping("removeByIds")
    public ResponseBodyApi removeByIds(@RequestBody List<String> idList) {
        roleService.remove(idList);
        return new ResponseBodyApi();
    }


}
