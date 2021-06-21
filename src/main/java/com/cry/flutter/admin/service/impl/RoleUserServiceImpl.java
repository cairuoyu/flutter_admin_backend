package com.cry.flutter.admin.service.impl;

import com.cry.flutter.admin.entity.RoleUser;
import com.cry.flutter.admin.mapper.RoleUserMapper;
import com.cry.flutter.admin.service.IRoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-08-20
 */
@Service("roleUserService")
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements IRoleUserService {

}
