package com.cry.flutter.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cry.flutter.admin.entity.MessageReplay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cry.flutter.admin.vo.MessageReplayVO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cairuoyu
 * @since 2021-03-30
 */
public interface MessageReplayMapper extends BaseMapper<MessageReplay> {

    IPage<MessageReplayVO> queryPage(Page<MessageReplayVO> page, Wrapper ew);
}
