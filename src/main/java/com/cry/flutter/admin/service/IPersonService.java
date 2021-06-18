package com.cry.flutter.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.entity.Person;

public interface IPersonService extends IService<Person> {
    Page<Person> page(RequestBodyApi<Person> requestBodyApi);
}
