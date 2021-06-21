package com.cry.flutter.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.entity.Person;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-11-01
 */
public interface IPersonService extends IService<Person> {
    Page<Person> page(RequestBodyApi<Person> requestBodyApi);
}
