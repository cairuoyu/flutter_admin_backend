package com.cry.flutter.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.Message;
import com.cry.flutter.admin.entity.MessageReplay;
import com.cry.flutter.admin.service.IMessageReplayService;
import com.cry.flutter.admin.service.IMessageService;
import com.cry.flutter.admin.utils.RequestUtil;
import com.cry.flutter.admin.vo.MessageReplayVO;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.cry.flutter.admin.constants.Constant.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cairuoyu
 * @since 2020-11-01
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource(name = "messageReplayServiceImpl")
    IMessageReplayService messageReplayService;

    @Resource(name = "messageServiceImpl")
    IMessageService messageService;

    @PostMapping("replayPage")
    public ResponseBodyApi<IPage<MessageReplayVO>> replayPage(@RequestBody RequestBodyApi<Message> requestBodyApi) {
//        Message message = requestBodyApi.getParams();
//        Page<MessageReplay> page = messageReplayService.page(requestBodyApi.getPage(), new QueryWrapper<MessageReplay>().lambda().eq(MessageReplay::getMessageId, message.getId()));
        IPage<MessageReplayVO> page = messageReplayService.queryPage(requestBodyApi);
        return new ResponseBodyApi<>(page);
    }

    @PostMapping("replayList")
    public ResponseBodyApi replayList(@RequestBody String messageId) {
        List<MessageReplay> list = messageReplayService.list(new QueryWrapper<MessageReplay>().lambda().eq(MessageReplay::getMessageId, messageId));
        return new ResponseBodyApi(list);
    }

    @PostMapping("replayCommit")
    public ResponseBodyApi replayCommit(@RequestBody MessageReplay messageReplay) {
        messageReplay.setCreaterId(RequestUtil.getCurrentUserId());
        messageReplayService.save(messageReplay);
        return new ResponseBodyApi();
    }

    @PostMapping("save")
    public ResponseBodyApi save(@RequestBody Message message) {
        messageService.save(message);
        return new ResponseBodyApi();
    }

    @PostMapping("page")
    public ResponseBodyApi<Page<Message>> page(@RequestBody RequestBodyApi<Message> requestBodyApi) {
        Page<Message> page = messageService.page(requestBodyApi.getPage(), getQueryWrapper(requestBodyApi));
        return new ResponseBodyApi<>(page);
    }

    @PostMapping("removeByIds")
    public ResponseBodyApi removeByIds(@RequestBody List<String> idList) {
        if (!ID_ADMIN2.equals(RequestUtil.getCurrentUserId())) {
            return new ResponseBodyApi(false, MESSAGE_NO_PERMISSIONS);
        }
        messageService.removeByIds(idList);
        return new ResponseBodyApi();
    }

    private LambdaQueryWrapper<Message> getQueryWrapper(RequestBodyApi<Message> requestBodyApi) {
        Message image = requestBodyApi.getParams();
        LambdaQueryWrapper<Message> queryWrapper = new QueryWrapper<Message>().lambda();
        if (image != null) {
            queryWrapper = queryWrapper.like(!StringUtils.isEmpty(image.getTitle()), Message::getTitle, image.getTitle());
        }
        return queryWrapper.orderByDesc(Message::getCreateTime);
    }

}
