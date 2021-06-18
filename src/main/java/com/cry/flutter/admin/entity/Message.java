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
 * @since 2020-11-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Message extends BizBaseEntity {

    private static final long serialVersionUID = 1L;

    private String title;

    private String content;

    private String state;

    private Integer commentCount;

    private Integer appreciateCount;

    private String createrId;



}
