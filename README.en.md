# flutter_admin_backend


![GitHub](https://img.shields.io/github/license/cairuoyu/flutter_admin)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/cairuoyu/flutter_admin_backend)
![GitHub repo size](https://img.shields.io/github/repo-size/cairuoyu/flutter_admin_backend?color=yellow)
![Flutter version](https://img.shields.io/badge/java-1.8-red)

> [简体中文](./README.md) | English

> Using Java, Flutter implementation of a backend management system. This project is the back end, and the corresponding front end is https://github.com/cairuoyu/flutter_admin。

---
## Functional
* User Registration
* Login And Logout
* Function Menu
* Dashboard
* Role Management
* User Management
* Menu Management
* Article Management
* upload Picture
* Video Upload
* Personnel Management
* Data Dictionary Management
* Message
* My Information
* Chart
* Globalization
* Language Switch
* Theme Switch
* Font Switch
* Standalone configuration file
* Component packaging
* Import or Export Excle

## Technology
| Name            | Technology          |
| --------------- | ------------------- |
| Language        | Java                |
| General Library | Spring、Spring Boot |
| Data            | Mybatis-plus        |
| Environment     | Maven               |
| Permissions     | JWT                 |

## The code structure
```
├─src
│  └─main
│      │  └─com
│      │      └─cry
│      │          └─flutter
│      │              └─admin
│      │                  ├─common
│      │                  ├─config
│      │                  ├─constants
│      │                  ├─controller
│      │                  ├─entity
│      │                  ├─mapper
│      │                  ├─service
│      │                  │  └─impl
│      │                  ├─utils
│      │                  ├─vo
│      │                  └─wrapper
│      └─resources
│          └─mapper
```

## Configuration application.yml
```bash
server:
  port: 8081

spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
#    配置数据库连接
    url: jdbc:mysql://ip:3306/sid?serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true
    username: us
    password: ps
    hikari:
      max-lifetime: 60000
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
  servlet:
    multipart:
      max-file-size: 10000000
      max-request-size: 10000000

debug: true
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  mapper-locations: classpath:/mapper/**.xml

file:
#  文件上传地址
  upload-path: G:\apache-tomcat-9.0.34-8093\webapps\download\
#  文件下载地址
  downloadPath: http://localhost:8093/download/


```

## packaging
```bash
mvn package
```

## run

```bash
java -jar .\target\flutter-admin-backend.jar
```

## live demo
http://www.cairuoyu.com/flutter_admin

Visit the website for more pictures：https://github.com/cairuoyu/flutter_admin

![image](http://cairuoyu.com/screenshots/flutter_admin1.gif)

## Join Discussion Group
### Add me to WeChat to pull you into the group
![image](http://cairuoyu.com/screenshots/qrcode_wechat_cry.png)

### QQ
851796663

