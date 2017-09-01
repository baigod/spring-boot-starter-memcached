# spring-boot-starter-memcached
不知道spring boot为什么没有memcached的集成

随便写一个用用

```java
@SpringBootApplication
@EnableMemcached
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProviderApplication.class, args);
	}
}

```


#application.properties配置如下

```yaml
memcached.servers=127.0.0.1:11211

```



##有问题反馈
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流
* QQ: 15174834



在兴趣的驱动下,写一个`免费`的东西，有欣喜，也还有汗水，希望你喜欢我的作品，同时也能支持一下。
当然，有钱捧个钱场(微信支付），没钱捧个人场，谢谢各位。

捐助开发者<br>
![pay](http://cdn.51szzc.com/custom/pay2luheng.png?v1)