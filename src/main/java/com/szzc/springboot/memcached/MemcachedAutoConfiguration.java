package com.szzc.springboot.memcached;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.szzc.springboot.memcached.utils.MemcachedLockUtils;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Locator;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

@Configuration
// @AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
@EnableConfigurationProperties(MemcachedProperties.class)
public class MemcachedAutoConfiguration {

	private final Logger logger = LoggerFactory.getLogger(MemcachedAutoConfiguration.class);

	@Autowired
	private MemcachedProperties memcachedProperties;

	@Bean
	public MemcachedClient memcachedClient() throws IOException {
		ConnectionFactoryBuilder connectionFactoryBuilder = new ConnectionFactoryBuilder();
		connectionFactoryBuilder.setProtocol(Protocol.valueOf(memcachedProperties.getProtocol()));
		connectionFactoryBuilder.setLocatorType(Locator.valueOf(memcachedProperties.getLocatorType()));
		connectionFactoryBuilder.setOpTimeout(memcachedProperties.getOpTimeout());
		if (!StringUtils.isEmpty(memcachedProperties.getUsername())
				&& !StringUtils.isEmpty(memcachedProperties.getPassword())) {
			PlainCallbackHandler h = new PlainCallbackHandler(memcachedProperties.getUsername(),
					memcachedProperties.getPassword());
			AuthDescriptor to = new AuthDescriptor(new String[] { "PLAIN" }, h);
			connectionFactoryBuilder.setAuthDescriptor(to);
			logger.info("init memcached[servers:{} protocol:{} locator_type:{} op_timeout:{} username:{} password:{}]",
					memcachedProperties.getServers(), memcachedProperties.getServers(),
					memcachedProperties.getLocatorType(), memcachedProperties.getOpTimeout(),
					memcachedProperties.getUsername(), blank(memcachedProperties.getPassword()));
		} else {
			logger.info("init memcached[servers:{} protocol:{} locator_type:{} op_timeout:{}]",
					memcachedProperties.getServers(), memcachedProperties.getServers(),
					memcachedProperties.getLocatorType(), memcachedProperties.getOpTimeout());
		}
		return new MemcachedClient(connectionFactoryBuilder.build(),
				AddrUtil.getAddresses(Arrays.asList(memcachedProperties.getServers())));
	}

	private Object blank(String password) {
		if (password.length() > 2) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < password.length() - 2; i++) {
				sb.append("*");
			}
			return password.substring(0, 1) + sb.toString()
					+ password.substring(password.length() - 1, password.length());
		}
		return password;
	}

	@Bean
	public MemcachedLockUtils memcachedLockUtils() {
		return new MemcachedLockUtils();
	}
}
