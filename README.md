# spring-boot-starter-memcached
I don't know why spring boot does not have an integration of memcached

Mode of use
```java
@SpringBootApplication
@EnableMemcached
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProviderApplication.class, args);
	}
}

```


# application.properties configuration

```yaml
memcached.servers=127.0.0.1:11211

```

The parent of this project can be modified by itself. I use the upper POM.xml.
```java
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>1.5.6.RELEASE</version>
</parent>
```

You can add three party dependence to your own needs


Donation developer (ETH)<br>
0x23b96A20Fae711ED6D286feAEED437a6831e3dD7