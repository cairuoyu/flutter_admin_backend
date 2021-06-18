package com.cry.flutter.admin.common;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cry.flutter.admin.constants.ConstantDict;
import com.cry.flutter.admin.entity.Dict;
import com.cry.flutter.admin.service.IDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class DictExportExcelListener extends AnalysisEventListener<Dict> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictExportExcelListener.class);
    private static final int BATCH_COUNT = 5;
    List<Dict> list = new ArrayList<Dict>();
    IDictService dictService;

    public DictExportExcelListener(IDictService dictService) {
        this.dictService = dictService;
    }

    @Override
    public void invoke(Dict dict, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", dict.getName());
        int count = dictService.count(new QueryWrapper<Dict>().lambda().eq(Dict::getCode, dict.getCode()));
        if (count > 0) {
            LOGGER.info("代码"+dict.getCode()+"已存在");
            return ;
        }
        dict.setState(ConstantDict.CODE_YESNO_NO);
        list.add(dict);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        dictService.saveBatch(list);
        LOGGER.info("存储数据库成功！");
    }
}

