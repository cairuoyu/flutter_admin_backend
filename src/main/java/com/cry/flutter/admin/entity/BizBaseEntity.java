package com.cry.flutter.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BizBaseEntity extends BaseEntity implements Serializable {
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_Time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
