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
 * @since 2021-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Article extends BizBaseEntity {

    private static final long serialVersionUID = 1L;

    private String title;

    private String titleSub;

    private String author;

    private String status;

    private String publishTime;

    private Integer orderBy;

    private String createrId;

    private String updateId;

    private String fileUrl;


}
