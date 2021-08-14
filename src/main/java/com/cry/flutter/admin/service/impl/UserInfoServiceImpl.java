package com.cry.flutter.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.entity.Dept;
import com.cry.flutter.admin.entity.UserInfo;
import com.cry.flutter.admin.mapper.UserInfoMapper;
import com.cry.flutter.admin.service.IDeptService;
import com.cry.flutter.admin.service.IUserInfoService;
import com.cry.flutter.admin.utils.RequestUtil;
import com.cry.flutter.admin.wrapper.UserInofWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-11-01
 */
@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
    @Resource(name = "deptServiceImpl")
    IDeptService deptService;

    @Override
    public IPage<UserInofWrapper> page(RequestBodyApi<UserInfo> requestBodyApi) {

        Page requestBodyApiPage = requestBodyApi.getPage();
        if (CollectionUtils.isEmpty(requestBodyApiPage.getOrders())) {
            requestBodyApiPage.setOrders(new ArrayList<OrderItem>() {{
                add(OrderItem.desc("update_time"));
            }});
        }
        UserInfo userInfo = requestBodyApi.getParams();
        LambdaQueryWrapper<UserInfo> queryWrapper = new QueryWrapper<UserInfo>().lambda();
        if (userInfo != null) {
            queryWrapper = queryWrapper.like(!StringUtils.isEmpty(userInfo.getName()), UserInfo::getName, userInfo.getName())
                    .eq(!StringUtils.isEmpty(userInfo.getDeptId()), UserInfo::getDeptId, userInfo.getDeptId())
                    .eq(!StringUtils.isEmpty(userInfo.getGender()), UserInfo::getGender, userInfo.getGender());
        }
        IPage result = this.baseMapper.queryPage(requestBodyApiPage, queryWrapper);
        return result;
    }

    @Override
    public UserInofWrapper getCurrentUserInfo() {
        UserInfo userInfo = getOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getUserId, RequestUtil.getCurrentUserId()));
        Dept dept = deptService.getOne(new QueryWrapper<Dept>().lambda().eq(Dept::getId, userInfo.getDeptId()));

        UserInofWrapper userInofWrapper = new UserInofWrapper();
        BeanUtils.copyProperties(userInfo, userInofWrapper);
        if (dept != null) {
            userInofWrapper.setDeptName(dept.getName());
        }
        return userInofWrapper;
    }
}
