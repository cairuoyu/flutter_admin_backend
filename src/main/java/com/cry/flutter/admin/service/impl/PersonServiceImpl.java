package com.cry.flutter.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.mapper.PersonMapper;
import com.cry.flutter.admin.entity.Person;
import com.cry.flutter.admin.service.IPersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



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
@Service("personService")
@Transactional
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {
    @Override
    public Page<Person> page(RequestBodyApi<Person> requestBodyApi) {

        return null;
    }
}
