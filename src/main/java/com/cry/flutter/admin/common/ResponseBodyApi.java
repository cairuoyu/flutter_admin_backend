package com.cry.flutter.admin.common;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author cairuoyu
 * @homepage: http://cairuoyu.com
 * @github: https://github.com/cairuoyu/flutter_admin_backend
 * @since 2020-10-12
 */
@Data
public class ResponseBodyApi<T> implements Serializable {
    private String code = "0";
    private boolean success = true;
    private String message;
    private T data;
    public ResponseBodyApi(){}

    public ResponseBodyApi(String code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public ResponseBodyApi(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseBodyApi(T data) {
        this.data = data;
    }
}
