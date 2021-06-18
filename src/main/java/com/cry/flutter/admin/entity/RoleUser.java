package com.cry.flutter.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author cairuoyu
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleUser  extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String roleId;

    private String createId;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;



}
