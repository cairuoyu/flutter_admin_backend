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
 * @since 2020-05-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Menu extends BizBaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String nameEn;

    private String subsystemId;

    private String icon;

    private String pid;

    private String url;

    private String module;

    private String remark;

    private Integer orderBy;


}
