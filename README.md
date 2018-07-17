# spring-cloud-demo
spring cloud 学习笔记
### 自动更新配置中心的值
1. 添加actuator依赖
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
2. 在使用@Value("xxx")的类上面@RefreshScope
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
