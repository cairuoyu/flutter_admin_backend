package com.cry.flutter.admin.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author cairuoyu
 * @since 2020-09-26
 */
@Data
public class Dict extends BizBaseEntity {

    private static final long serialVersionUID = 1L;
    @ExcelProperty("代码")
    private String code;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("状态")
    private String state;

    private String createrId;


}
