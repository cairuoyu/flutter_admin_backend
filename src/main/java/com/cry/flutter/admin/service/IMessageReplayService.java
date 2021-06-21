package com.cry.flutter.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.entity.Message;
import com.cry.flutter.admin.entity.MessageReplay;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cry.flutter.admin.vo.MessageReplayVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2021-03-30
 */
public interface IMessageReplayService extends IService<MessageReplay> {

    IPage<MessageReplayVO> queryPage(RequestBodyApi<Message> requestBodyApi);
}
