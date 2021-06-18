package com.cry.flutter.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cry.flutter.admin.mapper.UserMapper;
import com.cry.flutter.admin.entity.User;
import com.cry.flutter.admin.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
