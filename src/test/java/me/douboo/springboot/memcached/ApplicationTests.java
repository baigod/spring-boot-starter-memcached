package me.douboo.springboot.memcached;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

@RunWith(SpringRunner.class)
@PropertySource("classpath:application.properties")
@EnableMemcached
public class ApplicationTests {

	protected final Logger LOG = LoggerFactory.getLogger(ApplicationTests.class);

	@Autowired
	private MemcachedClient memcachedClient;

	@Test
	public void testCUD() throws Exception {
		String key = "memcached_demo_key";
		OperationFuture<Boolean> set = this.memcachedClient.set(key, 10, System.currentTimeMillis());
		assert set.get();
		Object object = this.memcachedClient.get(key);
		System.err.println(object + "");
		assert null != object;
	}

}
