package com.cry.flutter.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cry.flutter.admin.common.FileProperties;
import com.cry.flutter.admin.common.Operation;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.Video;
import com.cry.flutter.admin.service.IFileService;
import com.cry.flutter.admin.service.IVideoService;
import com.cry.flutter.admin.utils.FileUtil;
import com.cry.flutter.admin.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cairuoyu
 * @since 2020-05-29
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private FileProperties fileProperties;
    @Resource(name = "fileServiceImpl")
    IFileService fileService;
    @Resource(name = "videoServiceImpl")
    IVideoService videoService;

    @GetMapping("all")
    public ResponseBodyApi<List<Video>> all() {
        return new ResponseBodyApi<>(videoService.list());
    }

    @Operation()
    @PostMapping("list")
    public ResponseBodyApi<List<Video>> list(@RequestBody RequestBodyApi<Video> requestBodyApi) {
        return new ResponseBodyApi<>(videoService.list(getQueryWrapper(requestBodyApi)));
    }


    @PostMapping("page")
    public ResponseBodyApi<Page<Video>> page(@RequestBody RequestBodyApi<Video> requestBodyApi) {
        Page<Video> page = videoService.page(requestBodyApi.getPage(), getQueryWrapper(requestBodyApi));
        return new ResponseBodyApi<Page<Video>>(page);
    }

    private LambdaQueryWrapper<Video> getQueryWrapper(RequestBodyApi<Video> requestBodyApi) {
        Video video = requestBodyApi.getParams();
        LambdaQueryWrapper<Video> queryWrapper = new QueryWrapper<Video>().lambda();
        if (video != null) {
            queryWrapper = queryWrapper.like(!StringUtils.isEmpty(video.getTitle()), Video::getTitle, video.getTitle());
        }
        return queryWrapper.orderByDesc(Video::getCreateTime);
    }

    @PostMapping("upload")
    public ResponseBodyApi upload(@RequestParam("file") MultipartFile mf, Video video) {
        String filename = StringUtils.cleanPath(mf.getOriginalFilename());
        filename = FileUtil.codeFileName(filename, "avi");

        video.setCategoryId("1");
        video.setUrl(fileProperties.getDownloadPath() + filename);
        video.setCreaterId(RequestUtil.getCurrentUserId());
        videoService.save(video);

        fileService.storeFile(mf, video.getId(), filename);

        return new ResponseBodyApi();
    }

}
