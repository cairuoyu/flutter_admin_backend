package com.cry.flutter.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.RoleMenu;
import com.cry.flutter.admin.service.IRoleMenuService;
import com.cry.flutter.admin.vo.RoleMenuVO;
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
 * @since 2020-08-20
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {

    @Resource(name = "roleMenuService")
    IRoleMenuService roleMenuService;


//    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseBodyApi save(@RequestBody Map<String, Object> map) {
//        String roleId = (String) map.get("roleId");
//        List<Menu> menuList = (List<Menu>) map.get("menuList");
//        List<RoleMenu> roleMenuList = menuList.stream().map(v -> new RoleMenu(v.getId(), roleId)).collect(Collectors.toList());
//        roleMenuService.saveBatch(roleMenuList);
//        return new ResponseBodyApi();
//    }

    @PostMapping(value = "saveBatch", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBodyApi saveBatch(@RequestBody RoleMenuVO roleMenuVO) {
        List<RoleMenu> roleMenuList = roleMenuVO.getRoleMenuList();
        String roleId = roleMenuVO.getRoleId();
        roleMenuService.remove(new QueryWrapper<RoleMenu>().lambda().eq(RoleMenu::getRoleId, roleId)
                .exists("select 1 from menu t where t.id = menu_id and subsystem_id = '" + roleMenuVO.getSubsystemId() + "'"));
        if (!roleMenuList.isEmpty()) {
            roleMenuService.saveBatch(roleMenuList);
        }
        return new ResponseBodyApi();
    }

//    @PostMapping("removeBatch")
//    public ResponseBodyApi removeBatch(@RequestBody List<RoleMenu> roleMenuList) {
//        roleMenuList.forEach(v -> roleMenuService.remove(new QueryWrapper<RoleMenu>().lambda().eq(RoleMenu::getMenuId, v.getMenuId()).eq(RoleMenu::getRoleId, v.getRoleId())));
//        return new ResponseBodyApi();
//    }
//
//    @PostMapping("removeByIds")
//    public ResponseBodyApi removeByIds(@RequestBody List<String> idList) {
//        roleMenuService.removeByIds(idList);
//        return new ResponseBodyApi();
//    }
}
