package com.cry.flutter.admin.service.impl;

import com.cry.flutter.admin.common.FileProperties;
import com.cry.flutter.admin.entity.File;
import com.cry.flutter.admin.mapper.FileMapper;
import com.cry.flutter.admin.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-05-29
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {


    @Autowired
    private FileProperties fileProperties;

    @Override
    public void storeFile(MultipartFile mf, String bid, String filename) {
        String path = fileProperties.getUploadPath();
        Path targetLocation = Paths.get(path + filename);

        try {
            Files.copy(mf.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File();
        file.setBid(bid);
        file.setName(filename);
        file.setPath(path);
        file.setSize(mf.getSize());
        file.setType(filename.substring(filename.lastIndexOf(".")));
        save(file);
    }
}
