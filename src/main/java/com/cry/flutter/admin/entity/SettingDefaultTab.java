package com.cry.flutter.admin.entity;

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
 * @since 2021-08-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SettingDefaultTab extends BizBaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String nameEn;

    private String icon;

    private String userId;

    private String url;

    private String module;

    private String remark;

    private Integer orderBy;

    private String subsystemId;


}
