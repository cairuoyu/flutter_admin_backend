package com.cry.flutter.admin.entity;

import lombok.Data;

@Data
public class UserInfo  extends BizBaseEntity {
    private String userId;

    private String nickName;

    private String avatarUrl;

    private String gender;

    private String country;

    private String province;

    private String city;

    private String name;

    private String school;

    private String major;

    private String birthday;

    private String entrance;

    private String hometown;

    private String memo;

    private String deptId;

    private String userName;
}
