package com.cry.flutter.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.entity.UserInfo;

public interface IUserInfoService extends IService<UserInfo> {
    IPage page(RequestBodyApi<UserInfo> requestBodyApi);
}
