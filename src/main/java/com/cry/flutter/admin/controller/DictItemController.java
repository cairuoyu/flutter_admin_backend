package com.cry.flutter.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.Dict;
import com.cry.flutter.admin.entity.DictItem;
import com.cry.flutter.admin.service.IDictItemService;
import com.cry.flutter.admin.service.IDictService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-09-26
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/dictItem")
public class DictItemController {
    @Resource(name = "dictItemServiceImpl")
    IDictItemService dictItemService;
    @Resource(name = "dictServiceImpl")
    IDictService dictService;

    @GetMapping("all")
    public ResponseBodyApi<List<DictItem>> all() {
        return new ResponseBodyApi<>(dictItemService.list());
    }

    @PostMapping("list")
    public ResponseBodyApi<List<DictItem>> list(@RequestBody DictItem dictItem) {
        List<DictItem> list = dictItemService.list(new QueryWrapper<DictItem>().lambda().eq(DictItem::getDictId, dictItem.getDictId()));
        return new ResponseBodyApi<>(list);
    }

    @GetMapping("map")
    public ResponseBodyApi<Map<String, List<DictItem>>> map() {
        List<Dict> dictAll = dictService.list();
        List<DictItem> dictItemAll = dictItemService.list();

        Map<String, List<DictItem>> map = new HashMap<>();
        Map<String, String> idCodeMap = new HashMap<>();
        dictAll.forEach(v -> {
            idCodeMap.put(v.getId(), v.getCode());
            List<DictItem> dictItemList = new ArrayList<>();
            map.put(v.getCode(), dictItemList);
        });
        dictItemAll.forEach(v -> {
            List<DictItem> itemList = map.get(idCodeMap.get(v.getDictId()));
            if (itemList != null) {
                itemList.add(v);
            }
        });

        return new ResponseBodyApi(map);
    }

}
