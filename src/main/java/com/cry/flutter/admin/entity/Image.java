package com.cry.flutter.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author cairuoyu
 * @since 2020-05-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Image extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String title;

    private String url;

    private String categoryId;

    private Integer thumbs;

    private String memo;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    private String createrId;

}
