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
### 自动更新配置中心的值
- 在手动更新配置的基础上  
>1.在GitHubSettings上添加webhook  
>2.填写目标地址http://xx.xx.xx.xx/actuator/refresh  
>3.启动webhook  
- 目前使用webhook遇到严重问题，refresh接口不支持表单和其它内容，但是webhook发送post请求的时候自动添加了其它东西，导致错误

### Hystrix使用的坑
> hystrix主要用途是服务崩溃的时候调用fallbcak方法(服务降级)  
- feign使用hystrix  
> 1.只需要在配置文件中配置  
```yaml
feign:
  hystrix:
    enabled: true
```
> 2.在Feign调用类上注解,声明fallback接口  
```java
@FeignClient(value = "eureka-client",fallback = FeignFallback.class)
```
> 3.实现声明好的fallback接口,注册为bean
```java
@Component
```
- ribbon使用hystrix  
> 1.由于feign已经整合好了hystrix的依赖，所以不用导入hystrix依赖  
> 2.需要用到在主程序上@EnableHystrix  
> 3.需要在服务层的方法上面@HystrixCommand(fallback="xxxMethod")  
> 4....
