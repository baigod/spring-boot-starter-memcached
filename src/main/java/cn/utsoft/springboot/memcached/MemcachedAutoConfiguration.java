package cn.utsoft.springboot.memcached;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Locator;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

@Configuration
//@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
@EnableConfigurationProperties(MemcachedProperties.class)
public class MemcachedAutoConfiguration {

	@Autowired
	private MemcachedProperties memcachedProperties;

	@Bean
	public MemcachedClient memcachedClient() throws IOException {
		ConnectionFactoryBuilder connectionFactoryBuilder = new ConnectionFactoryBuilder();
		connectionFactoryBuilder.setProtocol(Protocol.valueOf(memcachedProperties.getProtocol()));
		connectionFactoryBuilder.setLocatorType(Locator.valueOf(memcachedProperties.getLocatorType()));
		connectionFactoryBuilder.setOpTimeout(memcachedProperties.getOpTimeout());
		if (!StringUtils.isEmpty(memcachedProperties.getUsername()) && !StringUtils.isEmpty(memcachedProperties.getPassword())) {
			PlainCallbackHandler h = new PlainCallbackHandler(memcachedProperties.getUsername(), memcachedProperties.getPassword());
			AuthDescriptor to = new AuthDescriptor(new String[] { "PLAIN" }, h);
			connectionFactoryBuilder.setAuthDescriptor(to);
		}
		return new MemcachedClient(connectionFactoryBuilder.build(), AddrUtil.getAddresses(Arrays.asList(memcachedProperties.getServers())));
	}
}
