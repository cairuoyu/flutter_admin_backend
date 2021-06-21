package com.cry.flutter.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.entity.UserInfo;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-11-01
 */
public interface IUserInfoService extends IService<UserInfo> {
    IPage page(RequestBodyApi<UserInfo> requestBodyApi);
}
