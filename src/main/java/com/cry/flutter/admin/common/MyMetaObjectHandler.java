package com.cry.flutter.admin.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cry.flutter.admin.utils.RequestUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();

        this.setFieldValByName("createTime", now, metaObject);
        this.setFieldValByName("updateTime", now, metaObject);
        this.setFieldValByName("createrId", RequestUtil.getCurrentUserId(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateId", RequestUtil.getCurrentUserId(), metaObject);
    }
}
