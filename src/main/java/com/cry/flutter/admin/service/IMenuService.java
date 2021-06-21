package com.cry.flutter.admin.service;

import com.cry.flutter.admin.entity.Menu;
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
 * @since 2020-05-11
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getMenuList(String userId);
}
