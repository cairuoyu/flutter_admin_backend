package com.cry.flutter.admin.entity;

import lombok.Data;

@Data
public class User extends BizBaseEntity {
    private String userName;
    private String password;
}
