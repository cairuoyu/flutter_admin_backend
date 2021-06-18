package com.cry.flutter.admin.controller;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cry.flutter.admin.common.DictExportExcelListener;
import com.cry.flutter.admin.common.FileProperties;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.constants.ConstantDict;
import com.cry.flutter.admin.entity.Dict;
import com.cry.flutter.admin.entity.DictItem;
import com.cry.flutter.admin.service.IDictItemService;
import com.cry.flutter.admin.service.IDictService;
import com.cry.flutter.admin.wrapper.DictWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cairuoyu
 * @since 2020-09-26
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private FileProperties fileProperties;
    @Resource(name = "dictItemServiceImpl")
    IDictItemService dictItemService;
    @Resource(name = "dictServiceImpl")
    IDictService dictService;


    @GetMapping("map")
    public ResponseBodyApi<Map<String, List<DictItem>>> map() {
        List<Dict> dictAll = dictService.list();
        List<DictItem> dictItemAll = dictItemService.list();

        Map<String, List<DictItem>> map = new HashMap<>();
        Map<String, String> idCodeMap = new HashMap<>();
        dictAll.forEach(v -> {
            idCodeMap.put(v.getId(), v.getCode());
            List<DictItem> dictItemList = new ArrayList<>();
            if (v.getCode() == null) {
                return;
            }
            map.put(v.getCode(), dictItemList);
        });
        dictItemAll.forEach(v -> {
            String code = idCodeMap.get(v.getDictId());
            if (code == null) {
                return;
            }
            List<DictItem> itemList = map.get(code);
            if (itemList != null) {
                itemList.add(v);
            }
        });

        return new ResponseBodyApi(map);
    }


    @PostMapping("importExcel")
    public ResponseBodyApi importExcel(@RequestParam("file") MultipartFile mf) {
        try {
            EasyExcel.read(mf.getInputStream(), Dict.class, new DictExportExcelListener(dictService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseBodyApi();
    }

    @PostMapping("exportExcel")
    public ResponseBodyApi<String> exportExcel(@RequestBody RequestBodyApi<Dict> requestBodyApi) {
        List<Dict> data = this.page(requestBodyApi).getData().getRecords();
        if (data.isEmpty()) {
            return new ResponseBodyApi<>(false, "没有需要导出的数据");
        }

        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("name");
        includeColumnFiledNames.add("code");

        String fileName = "dict" + System.currentTimeMillis() + ".xlsx";
        String uploadPath = fileProperties.getUploadPath();
        EasyExcel.write(uploadPath + fileName, Dict.class).sheet("数据字典").includeColumnFiledNames(includeColumnFiledNames).doWrite(data);
        String downloadUrl = fileProperties.getDownloadPath() + fileName;
        return new ResponseBodyApi<>(downloadUrl);
    }

    @PostMapping("page")
    public ResponseBodyApi<Page<Dict>> page(@RequestBody RequestBodyApi<Dict> requestBodyApi) {
        Dict dict = requestBodyApi.getParams();

        LambdaQueryWrapper<Dict> queryWrapper = null;
        if (dict != null) {
            queryWrapper = new QueryWrapper<Dict>().lambda()
                    .like(!StringUtils.isEmpty(dict.getCode()), Dict::getCode, dict.getCode())
                    .like(!StringUtils.isEmpty(dict.getName()), Dict::getName, dict.getName());
        }
        Page<Dict> page = dictService.page(requestBodyApi.getPage(), queryWrapper);
        return new ResponseBodyApi<>(page);
    }

    @PostMapping("saveOrUpdate")
    public ResponseBodyApi saveOrUpdate(@RequestBody DictWrapper dictWrapper) {
        Dict dict = dictWrapper.getDict();
        if (StringUtils.isEmpty(dict.getCode()) || StringUtils.isEmpty(dict.getName())) {
            return new ResponseBodyApi(false, "代码或名称不能为空");
        }
        int count = dictService.count(new QueryWrapper<Dict>().lambda().eq(Dict::getCode, dict.getCode()).ne(!StringUtils.isEmpty(dict.getId()), Dict::getId, dict.getId()));
        if (count > 0) {
            return new ResponseBodyApi(false, "此代码已存在");
        }
        dict.setState(ConstantDict.CODE_YESNO_NO);
        dictService.saveOrUpdate(dict);
        dictItemService.remove(new QueryWrapper<DictItem>().lambda().eq(DictItem::getDictId, dict.getId()));
        List<DictItem> dictItemList = dictWrapper.getDictItemList();
        dictItemList.forEach(v -> v.setDictId(dict.getId()));
        dictItemService.saveBatch(dictItemList);

        return new ResponseBodyApi();
    }

    @PostMapping("removeByIds")
    public ResponseBodyApi removeByIds(@RequestBody List<String> idList) {
        dictService.removeByIds(idList);
        return new ResponseBodyApi();
    }
}
