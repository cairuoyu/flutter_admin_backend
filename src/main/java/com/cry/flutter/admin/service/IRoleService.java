package com.cry.flutter.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cry.flutter.admin.entity.Role;
import com.cry.flutter.admin.entity.UserInfo;
import com.cry.flutter.admin.vo.MenuVO;
import com.cry.flutter.admin.vo.RoleMenuVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cairuoyu
 * @since 2020-08-14
 */
public interface IRoleService extends IService<Role> {

    public void remove(@RequestBody List<String> idList);

    public List<MenuVO> getMenu(RoleMenuVO roleMenuVO);

    public Page<UserInfo> getUnSelectedUserInfo(Page<UserInfo> page, Map<String, Object> map);

    public Page<UserInfo> getSelectedUserInfo(Page<UserInfo> page, Map<String, Object> map);

//    public Page<Menu> getUnSelectedMenu(Page<Menu> page, Role role);
//
//    public Page<Menu> getSelectedMenu(Page<Menu> page, Role role);
}
