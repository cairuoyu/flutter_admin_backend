package com.cry.flutter.admin.service.impl;

import com.cry.flutter.admin.entity.Dept;
import com.cry.flutter.admin.mapper.DeptMapper;
import com.cry.flutter.admin.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cairuoyu
 * @since 2021-01-18
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {


    @Override
    public List<Dept> query(Dept dept) {
        return this.baseMapper.query(dept);
    }
}
