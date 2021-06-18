package com.cry.flutter.admin.service;

import com.cry.flutter.admin.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cairuoyu
 * @since 2020-05-29
 */
public interface IFileService extends IService<File> {

    void storeFile(MultipartFile mf, String bid, String filename);
}
