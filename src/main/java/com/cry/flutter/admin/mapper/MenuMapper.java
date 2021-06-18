package com.cry.flutter.admin.mapper;

import org.apache.ibatis.annotations.Param;

import com.cry.flutter.admin.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cairuoyu
 * @since 2020-05-11
 */
public interface MenuMapper extends BaseMapper<Menu> {


    List<Menu> getMenuList(@Param("userId") String userId, @Param("subsystemId") String subsystemId);


}
