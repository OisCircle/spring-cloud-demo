# spring-cloud-demo
> spring cloud 学习笔记
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
#### feign使用hystrix  
> 1.由于feign已经整合好了hystrix的依赖，所以不用导入hystrix依赖  
只需要在配置文件中配置  
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
- 意外情况:一次调用fallback一次不调用fallback,循环往复
- 原因:注册了两个同名的服务,使用了feign调用,负载均衡算法一次调用一个,所以一个成功,一个失败
#### ribbon使用hystrix  
> 1.导入hystrix依赖  
> 2.需要用到在主程序上@EnableHystrix  
> 3.需要在服务层的方法上面@HystrixCommand(fallback="xxxMethod")  
> 4....

### Hystrix容错措施  
#### 服务降级  
> 当一个请求响应时间超过时限,自动调用降级方法,快速返回  
#### 依赖隔离  
> hystrix使用舱壁模式,将每一个请求进行线程隔离  
使得请求失败之后不会影响到其它请求  
#### 断路器  
> 当网络故障,务响应长,降级频繁.断路器开启,对请求直接调用降级处理方法  
断路器的启动有如下衡量标准  
- 快照时间窗:断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10秒。
- 请求总数下限:在快照时间窗内，必须满足请求总数下限才有资格根据熔断。默认为20，意味着在10秒内，如果该hystrix命令的调用此时不足20次，即时所有的请求都超时或其他原因失败，断路器都不会打开。
- 错误百分比下限:当请求总数在快照时间窗内超过了下限，比如发生了30次调用，如果在这30次调用中，有16次发生了超时异常，也就是超过50%的错误百分比，在默认设定50%下限情况下，这时候就会将断路器打开。
