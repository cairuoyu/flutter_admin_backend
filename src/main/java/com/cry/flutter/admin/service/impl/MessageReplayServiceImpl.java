package com.cry.flutter.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.entity.Message;
import com.cry.flutter.admin.entity.MessageReplay;
import com.cry.flutter.admin.mapper.MessageReplayMapper;
import com.cry.flutter.admin.service.IMessageReplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cry.flutter.admin.vo.MessageReplayVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2021-03-30
 */
@Service
public class MessageReplayServiceImpl extends ServiceImpl<MessageReplayMapper, MessageReplay> implements IMessageReplayService {
    @Override
    public IPage<MessageReplayVO> queryPage(RequestBodyApi<Message> requestBodyApi) {
        Message message = requestBodyApi.getParams();
        IPage result = this.baseMapper.queryPage(requestBodyApi.getPage(), new QueryWrapper<MessageReplay>().lambda().eq(MessageReplay::getMessageId, message.getId()));
        return result;
    }
}
