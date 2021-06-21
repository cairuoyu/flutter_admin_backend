package com.cry.flutter.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cry.flutter.admin.constants.ConstantDict;
import com.cry.flutter.admin.common.Operation;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.Subsystem;
import com.cry.flutter.admin.service.ISubsystemService;
import com.cry.flutter.admin.utils.RequestUtil;
import com.cry.flutter.admin.vo.SubsystemVO;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.cry.flutter.admin.constants.Constant.ID_ADMIN2;
import static com.cry.flutter.admin.constants.Constant.SUBSYSTEM_ID_FLUTTER_ADMIN;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2021-01-04
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/subsystem")
public class SubsystemController {


    @Resource(name = "subsystemService")
    ISubsystemService subsystemService;


    @Operation()
    @PostMapping("list")
    public ResponseBodyApi<List<Subsystem>> list(@RequestBody RequestBodyApi<SubsystemVO> requestBodyApi) {
        return new ResponseBodyApi<>(subsystemService.list(getQueryWrapper(requestBodyApi)));
    }

    private LambdaQueryWrapper<Subsystem> getQueryWrapper(RequestBodyApi<SubsystemVO> requestBodyApi) {
        SubsystemVO subsystemVO = requestBodyApi.getParams();
        LambdaQueryWrapper<Subsystem> queryWrapper = new QueryWrapper<Subsystem>().lambda();
        if (subsystemVO != null) {
            List<String> stateList = new ArrayList<>();
            stateList.add("-1");
            if (subsystemVO.getIsEnable()) {
                stateList.add(ConstantDict.CODE_YESNO_YES);
            }
            if (subsystemVO.getIsDisable()) {
                stateList.add(ConstantDict.CODE_YESNO_NO);
            }
            queryWrapper = queryWrapper
                    .eq(!StringUtils.isEmpty(subsystemVO.getSubsystemId()), Subsystem::getId, subsystemVO.getSubsystemId())
                    .like(!StringUtils.isEmpty(subsystemVO.getCode()), Subsystem::getCode, subsystemVO.getCode())
                    .like(!StringUtils.isEmpty(subsystemVO.getName()), Subsystem::getName, subsystemVO.getName())
                    .in(!(subsystemVO.getIsEnable() && subsystemVO.getIsDisable()), Subsystem::getState, stateList);

        }
        return queryWrapper;
    }

    @PostMapping("page")
    public ResponseBodyApi<Page<Subsystem>> page(@RequestBody RequestBodyApi<SubsystemVO> requestBodyApi) {
        Page<Subsystem> page = subsystemService.page(requestBodyApi.getPage(), getQueryWrapper(requestBodyApi));
        return new ResponseBodyApi<>(page);
    }

    @PostMapping("getById")
    public ResponseBodyApi getById(@RequestBody Subsystem subsystem) {
        return new ResponseBodyApi<Subsystem>(subsystemService.getById(subsystem.getId()));
    }

    @PostMapping("saveOrUpdate")
    public ResponseBodyApi saveOrUpdate(@RequestBody Subsystem subsystem) {
        if (!ID_ADMIN2.equals(RequestUtil.getCurrentUserId()) && SUBSYSTEM_ID_FLUTTER_ADMIN.equals(subsystem.getId())) {
            return new ResponseBodyApi(false, "此系统不能操作");
        }
        int count = subsystemService.count(new QueryWrapper<Subsystem>().lambda().eq(Subsystem::getCode, subsystem.getCode()).ne(!StringUtils.isEmpty(subsystem.getId()), Subsystem::getId, subsystem.getId()));
        if (count > 0) {
            return new ResponseBodyApi(false, "此代码已存在");
        }
        count = subsystemService.count(new QueryWrapper<Subsystem>().lambda().eq(Subsystem::getName, subsystem.getName()).ne(!StringUtils.isEmpty(subsystem.getId()), Subsystem::getId, subsystem.getId()));
        if (count > 0) {
            return new ResponseBodyApi(false, "此名称已存在");
        }
        subsystemService.saveOrUpdate(subsystem);
        return new ResponseBodyApi();
    }

    @PostMapping("removeByIds")
    public ResponseBodyApi removeByIds(@RequestBody List<String> idList) {
        if (!ID_ADMIN2.equals(RequestUtil.getCurrentUserId()) && idList.contains(SUBSYSTEM_ID_FLUTTER_ADMIN)) {
            return new ResponseBodyApi(false, "此系统不能操作");
        }
        subsystemService.removeByIds(idList);
        return new ResponseBodyApi();
    }


}
