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
 * @since 2020-05-11
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getMenuList(String userId);
}
