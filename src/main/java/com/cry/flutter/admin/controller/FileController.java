package com.cry.flutter.admin.controller;


import com.cry.flutter.admin.common.FileProperties;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.File;
import com.cry.flutter.admin.service.IFileService;
import com.cry.flutter.admin.service.IImageService;
import com.cry.flutter.admin.utils.FileUtil;
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
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-05-29
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileProperties fileProperties;
    @Resource(name = "fileServiceImpl")
    IFileService fileService;
    @Resource(name = "imageServiceImpl")
    IImageService imageService;

    @GetMapping("list")
    public ResponseBodyApi<List<File>> list() {
        return new ResponseBodyApi<>(fileService.list());
    }

    @PostMapping("upload")
    public ResponseBodyApi upload(@RequestParam("file") MultipartFile mf) {
        String filename = StringUtils.cleanPath(mf.getOriginalFilename());
        filename = FileUtil.codeFileName(filename, "png");
        fileService.storeFile(mf, null, filename);

        String path = fileProperties.getDownloadPath() + filename;
        return new ResponseBodyApi(path);
    }
}
