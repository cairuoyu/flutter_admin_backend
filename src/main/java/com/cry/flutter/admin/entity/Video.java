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
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-06-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Video extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String title;

    private String categoryId;

    private Integer thumbs;

    private String memo;

    private String url;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    private String createrId;


}
