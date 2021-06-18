package com.cry.flutter.admin.vo;

import com.cry.flutter.admin.entity.RoleMenu;
import lombok.Data;

import java.util.List;

@Data
public class RoleMenuVO {
    String roleId;
    String subsystemId;
    List<RoleMenu> roleMenuList;
}
