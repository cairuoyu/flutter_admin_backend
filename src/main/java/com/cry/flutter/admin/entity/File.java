package com.cry.flutter.admin.entity;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class File extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String bid;

    private String name;

    private String path;

    private String type;

    private Long size;

    private LocalDateTime createTime;

    private String createrId;


}
