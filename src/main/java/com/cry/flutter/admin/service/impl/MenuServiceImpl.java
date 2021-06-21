package com.cry.flutter.admin.service.impl;

import com.cry.flutter.admin.constants.Constant;
import com.cry.flutter.admin.entity.Menu;
import com.cry.flutter.admin.mapper.MenuMapper;
import com.cry.flutter.admin.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-05-11
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> getMenuList(String userId) {
        return this.baseMapper.getMenuList(userId, Constant.SUBSYSTEM_ID_FLUTTER_ADMIN);
    }

}
