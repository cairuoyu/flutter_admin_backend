package com.cry.flutter.admin.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

@Data
public class RequestBodyApi<T> implements Serializable {
    Page page;
    T params;
}
