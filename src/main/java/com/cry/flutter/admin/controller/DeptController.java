package com.cry.flutter.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cry.flutter.admin.common.RequestBodyApi;
import com.cry.flutter.admin.common.ResponseBodyApi;
import com.cry.flutter.admin.entity.Dept;
import com.cry.flutter.admin.entity.UserInfo;
import com.cry.flutter.admin.service.IDeptService;
import com.cry.flutter.admin.service.IUserInfoService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author cairuoyu
 * @since 2021-01-13
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource(name = "deptServiceImpl")
    IDeptService deptService;

    @Resource(name = "userInfoService")
    IUserInfoService userInfoService;

    @PostMapping("list")
    public ResponseBodyApi<List<Dept>> list(@RequestBody RequestBodyApi<Dept> requestBody) {
        Dept dept = requestBody.getParams();
        if (dept != null && !StringUtils.isEmpty(dept.getName())) {
            return new ResponseBodyApi<>(deptService.query(dept));
        }
        return new ResponseBodyApi<>(deptService.list(new QueryWrapper<Dept>().lambda()
                .orderByAsc(Dept::getOrderBy)));
    }

    @PostMapping("saveOrUpdate")
    public ResponseBodyApi saveOrUpdate(@RequestBody Dept dept) {
        deptService.saveOrUpdate(dept);
        return new ResponseBodyApi();
    }

    @PostMapping("removeByIds")
    public ResponseBodyApi removeByIds(@RequestBody List<String> idList) {
        int count = userInfoService.count(new QueryWrapper<UserInfo>().lambda().in(UserInfo::getDeptId, idList));
        if (count > 0) {
            return new ResponseBodyApi(false, "此部门已被使用，不能删除");
        }
        deptService.removeByIds(idList);
        return new ResponseBodyApi();
    }
}
