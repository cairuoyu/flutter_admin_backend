package com.cry.flutter.admin.controller;


import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.MenuDemo;
import com.cry.flutter.admin.service.IMenuDemoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cairuoyu
 * @since 2020-05-11
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/menuDemo")
public class MenuDemoController {


    @Resource(name = "menuDemoServiceImpl")
    IMenuDemoService menuDemoService;

    @PostMapping("list")
    public ResponseBodyApi<List<MenuDemo>> list() {
        return new ResponseBodyApi<>(menuDemoService.list());
    }

    @PostMapping("saveOrUpdate")
    public ResponseBodyApi saveOrUpdate(@RequestBody MenuDemo menuDemo) {
        menuDemoService.saveOrUpdate(menuDemo);
        return new ResponseBodyApi();
    }

    @PostMapping("removeByIds")
    public ResponseBodyApi removeByIds(@RequestBody List<String> idList) {
        menuDemoService.removeByIds(idList);
        return new ResponseBodyApi();
    }
}
