package cn.utsoft.springboot.memcached;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "memcached")
@Component
public class MemcachedProperties {

	private String servers = "localhost:11211";
	private String protocol = "BINARY";
	private String locatorType = "CONSISTENT";
	private long opTimeout = 1000L;
	private String username;
	private String password;
	private boolean health= false;

	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getLocatorType() {
		return locatorType;
	}

	public void setLocatorType(String locatorType) {
		this.locatorType = locatorType;
	}

	public long getOpTimeout() {
		return opTimeout;
	}

	public void setOpTimeout(long opTimeout) {
		this.opTimeout = opTimeout;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isHealth() {
		return health;
	}

	public void setHealth(boolean health) {
		this.health = health;
	}
	
	

}
