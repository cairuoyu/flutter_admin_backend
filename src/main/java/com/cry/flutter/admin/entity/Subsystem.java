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
 * @since 2021-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Subsystem extends BizBaseEntity {

    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private String url;

    private String orderBy;

    private String remark;

    private String state;

    private String createrId;

    private String updateId;


}
