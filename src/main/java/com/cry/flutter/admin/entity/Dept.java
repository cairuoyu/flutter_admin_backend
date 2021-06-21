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
 * @since 2021-01-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Dept extends BizBaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String nameShort;

    private String pid;

    private String headerId;

    private String fun;

    private String remark;

    private Integer orderBy;


    private String createrId;

    private String updateId;


}
