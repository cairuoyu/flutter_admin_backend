package com.cry.flutter.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.Menu;
import com.cry.flutter.admin.service.IMenuService;
import com.cry.flutter.admin.utils.RequestUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.cry.flutter.admin.constants.Constant.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-05-11
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/menu")
public class MenuController {


    @Resource(name = "menuServiceImpl")
    IMenuService menuService;


    @PostMapping("listAuth")
    public ResponseBodyApi<List<Menu>> listAuth(@RequestBody String subsystemId) {
        String userId = RequestUtil.getCurrentUserId();
        System.out.println(userId);
        if (ID_ADMIN.equals(userId) || ID_ADMIN1.equals(userId) || ID_ADMIN2.equals(userId)) {
            return new ResponseBodyApi<>(menuService.list(new QueryWrapper<Menu>().lambda()
                    .eq(Menu::getSubsystemId, SUBSYSTEM_ID_FLUTTER_ADMIN)
                    .orderByAsc(Menu::getOrderBy)));
        }
        return new ResponseBodyApi<>(menuService.getMenuList(RequestUtil.getCurrentUserId(), subsystemId));
    }

    @PostMapping("list")
    public ResponseBodyApi<List<Menu>> list(@RequestBody RequestBodyApi<Menu> requestBody) {
        Menu menu = requestBody.getParams();
        return new ResponseBodyApi<>(menuService.list(new QueryWrapper<Menu>().lambda()
                .eq(!StringUtils.isEmpty(menu.getSubsystemId()), Menu::getSubsystemId, menu.getSubsystemId())
                .orderByAsc(Menu::getOrderBy)));
    }

    @PostMapping("saveOrUpdate")
    public ResponseBodyApi saveOrUpdate(@RequestBody Menu menu) {
        if (!ID_ADMIN2.equals(RequestUtil.getCurrentUserId()) && SUBSYSTEM_ID_FLUTTER_ADMIN.equals(menu.getSubsystemId())) {
            return new ResponseBodyApi(false, "此系统的所有菜单不能操作");
        }
        menuService.saveOrUpdate(menu);
        return new ResponseBodyApi();
    }

    @PostMapping("removeByIds")
    public ResponseBodyApi removeByIds(@RequestBody List<String> idList) {
        if (!ID_ADMIN2.equals(RequestUtil.getCurrentUserId())) {
            int count = menuService.count(new QueryWrapper<Menu>().lambda().eq(Menu::getSubsystemId, SUBSYSTEM_ID_FLUTTER_ADMIN).in(Menu::getId, idList));
            if (count > 0) {
                return new ResponseBodyApi(false, "此系统的所有菜单不能操作");
            }
        }
        menuService.removeByIds(idList);
        return new ResponseBodyApi();
    }
}
