package com.cry.flutter.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cry.flutter.admin.common.FileProperties;
import com.cry.flutter.admin.common.Operation;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.Image;
import com.cry.flutter.admin.service.IFileService;
import com.cry.flutter.admin.service.IImageService;
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
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private FileProperties fileProperties;
    @Resource(name = "fileServiceImpl")
    IFileService fileService;
    @Resource(name = "imageServiceImpl")
    IImageService imageService;

    @GetMapping("all")
    public ResponseBodyApi<List<Image>> all() {
        return new ResponseBodyApi<>(imageService.list());
    }

    @Operation()
    @PostMapping("list")
    public ResponseBodyApi<List<Image>> list(@RequestBody RequestBodyApi<Image> requestBodyApi) {
        return new ResponseBodyApi<>(imageService.list(getQueryWrapper(requestBodyApi)));
    }

    @PostMapping("page")
    public ResponseBodyApi<Page<Image>> page(@RequestBody RequestBodyApi<Image> requestBodyApi) {
        Page<Image> page = imageService.page(requestBodyApi.getPage(), getQueryWrapper(requestBodyApi));
        return new ResponseBodyApi<Page<Image>>(page);
    }

    private LambdaQueryWrapper<Image> getQueryWrapper(RequestBodyApi<Image> requestBodyApi) {
        Image image = requestBodyApi.getParams();
        LambdaQueryWrapper<Image> queryWrapper = new QueryWrapper<Image>().lambda();
        if (image != null) {
            queryWrapper = queryWrapper.like(!StringUtils.isEmpty(image.getTitle()), Image::getTitle, image.getTitle());
        }
        return queryWrapper.orderByDesc(Image::getCreateTime);
    }

    @PostMapping("upload")
    public ResponseBodyApi upload(@RequestParam("file") MultipartFile mf, Image image) {
        String filename = StringUtils.cleanPath(mf.getOriginalFilename());
        filename = FileUtil.codeFileName(filename, "png");

        image.setCategoryId("1");
        image.setUrl(fileProperties.getDownloadPath() + filename);
        image.setCreaterId(RequestUtil.getCurrentUserId());
        imageService.save(image);

        fileService.storeFile(mf, image.getId(), filename);

        return new ResponseBodyApi();
    }

}
