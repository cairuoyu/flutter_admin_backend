package com.cry.flutter.admin.mapper;
import java.util.List;

import com.cry.flutter.admin.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cairuoyu
 * @since 2021-01-18
 */
public interface DeptMapper extends BaseMapper<Dept> {
    List<Dept> query(@Param("dept") Dept dept);
}
