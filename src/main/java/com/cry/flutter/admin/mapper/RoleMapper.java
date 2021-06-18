package com.cry.flutter.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cry.flutter.admin.entity.UserInfo;
import com.cry.flutter.admin.entity.Role;
import com.cry.flutter.admin.vo.MenuVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author cairuoyu
 * @since 2020-08-14
 */
public interface RoleMapper extends BaseMapper<Role> {


//    @Select("SELECT (SELECT IFNULL((SELECT TRUE FROM role_menu t1 WHERE t1.menu_id = t.id AND t1.role_id = '${id}'), 0) ) checked, t.* FROM menu t ORDER BY t.order_by")
    List<MenuVO> getMenu(@Param("roleId") String roleId, @Param("subsystemId") String subsystemId);



//    @Select("select * from menu t where not exists (select 1 from role_menu t1 where t1.menu_id=t.id and t1.role_id='${role.id}')")
//    Page<Menu> getUnSelectedMenu(Page<Menu> page, Role role);
//
//    @Select("select * from menu t where exists (select 1 from role_menu t1 where t1.menu_id=t.id and t1.role_id='${role.id}')")
//    Page<Menu> getSelectedMenu(Page<Menu> page, Role role);

    @Select("select * from user_info t where not exists (select 1 from role_user t1 where t1.user_id=t.user_id and t1.role_id='${map.roleId}') and t.user_name like '%${map.userName}%'")
    Page<UserInfo> getUnSelectedUserInfo(Page<UserInfo> page, Map<String, Object> map);

    @Select("select * from user_info t where exists (select 1 from role_user t1 where t1.user_id=t.user_id and t1.role_id='${map.roleId}') and t.user_name like '%${map.userName}%'")
    Page<UserInfo> getSelectedUserInfo(Page<UserInfo> page, Map<String, Object> map);
}
