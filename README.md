# spring-cloud-demo
spring cloud 学习笔记
### 手动更新配置中心的值
1. 添加actuator依赖
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
2. 在使用@Value("xxx")的类上面
```java
@RefreshScope
```
3. 在yml(配置文件)里面添加如下信息
```yaml
management:
  endpoints:
    web:
      exposure:
        include: refresh
```
- 当配置修改的时候，访问localhost:xxxx/actuator/refresh，即可刷新配置,返回如下
```json
[
    "config.client.version",
    "dev-id"
]
```
### 手动更新配置中心的值
- 在手动更新配置的基础上  
>1.在GitHubSettings上添加webhook  
>2.填写目标地址http://xx.xx.xx.xx/actuator/refresh  
>3.启动webhook  
- 目前使用webhook遇到严重问题，refresh接口不支持表单和其它内容，但是webhook发送post请求的时候自动添加了其它东西，导致错误
