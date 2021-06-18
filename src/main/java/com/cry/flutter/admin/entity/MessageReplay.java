package com.cry.flutter.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

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
 * @since 2021-03-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MessageReplay extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String messageId;

    private String content;

    private String createrId;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


}
