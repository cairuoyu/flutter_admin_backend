package com.cry.flutter.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cry.flutter.admin.entity.Role;
import com.cry.flutter.admin.entity.RoleMenu;
import com.cry.flutter.admin.entity.RoleUser;
import com.cry.flutter.admin.entity.UserInfo;
import com.cry.flutter.admin.mapper.RoleMapper;
import com.cry.flutter.admin.service.IRoleMenuService;
import com.cry.flutter.admin.service.IRoleService;
import com.cry.flutter.admin.service.IRoleUserService;
import com.cry.flutter.admin.vo.MenuVO;
import com.cry.flutter.admin.vo.RoleMenuVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cairuoyu
 * @since 2020-08-14
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {


    @Resource(name = "roleUserService")
    IRoleUserService roleUserService;

    @Resource(name = "roleMenuService")
    IRoleMenuService roleMenuService;

    @Override
    public void remove(List<String> idList) {
        this.removeByIds(idList);
        roleUserService.remove(new QueryWrapper<RoleUser>().lambda().in(RoleUser::getRoleId, idList));
        roleMenuService.remove(new QueryWrapper<RoleMenu>().lambda().in(RoleMenu::getRoleId, idList));
    }

    @Override
    public List<MenuVO> getMenu(RoleMenuVO roleMenuVO) {
        return this.getBaseMapper().getMenu(roleMenuVO.getRoleId(), roleMenuVO.getSubsystemId());
    }

//    @Override
//    public Page<Menu> getUnSelectedMenu(Page<Menu> page, Role role) {
//        return this.getBaseMapper().getUnSelectedMenu(page, role);
//    }
//
//    @Override
//    public Page<Menu> getSelectedMenu(Page<Menu> page, Role role) {
//        return this.getBaseMapper().getSelectedMenu(page, role);
//    }

    @Override
    public Page<UserInfo> getUnSelectedUserInfo(Page<UserInfo> page, Map<String, Object> map) {
        return this.getBaseMapper().getUnSelectedUserInfo(page, map);
    }

    @Override
    public Page<UserInfo> getSelectedUserInfo(Page<UserInfo> page, Map<String, Object> map) {
        return this.getBaseMapper().getSelectedUserInfo(page, map);
    }
}
