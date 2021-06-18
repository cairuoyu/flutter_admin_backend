package com.cry.flutter.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cry.flutter.admin.mapper.SysLogMapper;
import com.cry.flutter.admin.entity.SysLog;
import com.cry.flutter.admin.service.ISysLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("sysLogService")
@Transactional
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
}
