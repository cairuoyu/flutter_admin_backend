package com.cry.flutter.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2021-03-11
 */
@Data
public class SysLog extends BaseEntity implements Serializable {
    private String userId;//用户名字
    private String userIp;//用户IP
    private String requestClass;//请求方法
    private String requestMethod;//请求方法
    private String requestDesc;//方法描述

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

}

