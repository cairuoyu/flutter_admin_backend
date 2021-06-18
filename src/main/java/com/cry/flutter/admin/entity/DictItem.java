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
 * @since 2020-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DictItem extends BizBaseEntity {

    private static final long serialVersionUID = 1L;

    private String dictId;

    private String code;

    private String name;


    private String createrId;


}
