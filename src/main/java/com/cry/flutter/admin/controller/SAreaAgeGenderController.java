package com.cry.flutter.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.SAreaAgeGender;
import com.cry.flutter.admin.service.ISAreaAgeGenderService;
import org.springframework.web.bind.annotation.*;

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
 * @since 2021-02-20
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/sAreaAgeGender")
public class SAreaAgeGenderController {

    @Resource(name = "sAreaAgeGenderService")
    ISAreaAgeGenderService sAreaAgeGenderService;


    @PostMapping("list")
    public ResponseBodyApi<List<SAreaAgeGender>> list(RequestBodyApi<SAreaAgeGender> requestBodyApi) {
        return new ResponseBodyApi<>(sAreaAgeGenderService.list(new QueryWrapper<SAreaAgeGender>().lambda().orderByDesc(SAreaAgeGender::getAge)));
    }


}
