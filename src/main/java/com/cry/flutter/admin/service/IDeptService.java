package com.cry.flutter.admin.service;

import com.cry.flutter.admin.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2021-01-18
 */
public interface IDeptService extends IService<Dept> {

    List<Dept> query(Dept dept);
}
