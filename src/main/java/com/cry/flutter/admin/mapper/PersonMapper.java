package com.cry.flutter.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cry.flutter.admin.entity.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper extends BaseMapper<Person> {
}
