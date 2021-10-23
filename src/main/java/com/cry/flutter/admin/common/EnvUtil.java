package com.cry.flutter.admin.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class EnvUtil {
    @Value("${token.timeout}")
    private Long tokenTimeout;

}
