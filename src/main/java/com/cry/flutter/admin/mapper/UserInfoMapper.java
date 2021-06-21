package com.cry.flutter.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cry.flutter.admin.wrapper.UserInofWrapper;
import com.cry.flutter.admin.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2021-03-30
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    IPage<UserInofWrapper> queryPage(Page<UserInfo> page, Wrapper ew);
}
