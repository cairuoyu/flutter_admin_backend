package com.cry.flutter.admin.entity;

import lombok.Data;

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
public class User extends BizBaseEntity {
    private String userName;
    private String password;
    private String face;
}
