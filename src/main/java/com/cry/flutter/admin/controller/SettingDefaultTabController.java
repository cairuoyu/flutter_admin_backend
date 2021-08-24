package com.cry.flutter.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.SettingDefaultTab;
import com.cry.flutter.admin.service.ISettingDefaultTabService;
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
 * @since 2021-08-24
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/settingDefaultTab")
public class SettingDefaultTabController {

    @Resource(name = "settingDefaultTabServiceImpl")
    ISettingDefaultTabService settingDefaultTabService;

    @PostMapping("list")
    public ResponseBodyApi<List<SettingDefaultTab>> list() {
        return new ResponseBodyApi<>(settingDefaultTabService.list(new QueryWrapper<SettingDefaultTab>().lambda().orderByDesc(SettingDefaultTab::getCreateTime)));
    }

    @PostMapping("saveOrUpdate")
    public ResponseBodyApi saveOrUpdate(@RequestBody SettingDefaultTab settingDefaultTab) {
        settingDefaultTabService.saveOrUpdate(settingDefaultTab);
        return new ResponseBodyApi();
    }

    @PostMapping("removeByIds")
    public ResponseBodyApi removeByIds(@RequestBody List<String> idList) {
        settingDefaultTabService.removeByIds(idList);
        return new ResponseBodyApi();
    }
}
