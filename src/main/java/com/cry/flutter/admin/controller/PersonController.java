package com.cry.flutter.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cry.flutter.admin.common.Operation;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.Person;
import com.cry.flutter.admin.service.IPersonService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Resource(name = "personService")
    IPersonService personService;

    @Operation()
    @PostMapping("list")
    public ResponseBodyApi<List<Person>> list(@RequestBody RequestBodyApi<Person> requestBodyApi) {
        return new ResponseBodyApi<>(personService.list(getQueryWrapper(requestBodyApi)));
    }

    private LambdaQueryWrapper<Person> getQueryWrapper(RequestBodyApi<Person> requestBodyApi) {
        Person person = requestBodyApi.getParams();
        LambdaQueryWrapper<Person> queryWrapper = new QueryWrapper<Person>().lambda();
        if (person != null) {
            queryWrapper = queryWrapper.like(!StringUtils.isEmpty(person.getName()), Person::getName, person.getName())
                    .eq(!StringUtils.isEmpty(person.getDeptId()), Person::getDeptId, person.getDeptId());
        }
        return queryWrapper;
    }

    @PostMapping("page")
    public ResponseBodyApi<Page<Person>> page(@RequestBody RequestBodyApi<Person> requestBodyApi) {
        Page<Person> page = personService.page(requestBodyApi.getPage(), getQueryWrapper(requestBodyApi));
        return new ResponseBodyApi<Page<Person>>(page);
    }

    @PostMapping("getById")
    public ResponseBodyApi getById(@RequestBody Person person) {
        return new ResponseBodyApi<Person>(personService.getById(person.getId()));
    }

    @PostMapping("saveOrUpdate")
    public ResponseBodyApi saveOrUpdate(@RequestBody Person person) {
        personService.saveOrUpdate(person);
        return new ResponseBodyApi();
    }
    @PostMapping("removeByIds")
    public ResponseBodyApi removeByIds(@RequestBody List<String> idList) {
        personService.removeByIds(idList);
        return new ResponseBodyApi();
    }


}
