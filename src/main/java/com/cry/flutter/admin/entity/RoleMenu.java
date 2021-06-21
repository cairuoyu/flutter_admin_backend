package com.cry.flutter.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleMenu  extends Model implements Serializable {
    public RoleMenu(String menuId, String roleId) {
        this.menuId = menuId;
        this.roleId = roleId;
    }

    private static final long serialVersionUID = 1L;

    private String menuId;

    private String roleId;

    private String createId;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


}
