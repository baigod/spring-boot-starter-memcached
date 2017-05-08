package cn.utsoft.springboot.memcached;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import net.spy.memcached.MemcachedClient;

@Configuration
@EnableConfigurationProperties(MemcachedProperties.class)
public class MemcachedHealthListener implements ApplicationListener<ContextRefreshedEvent> {
	private static Thread holdThread;

	@Autowired
	private MemcachedProperties memcachedProperties;

	@Autowired
	private MemcachedClient memcachedClient;

	private final Logger logger = LoggerFactory.getLogger(MemcachedHealthListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (memcachedProperties.isHealth()) {
			if (holdThread == null) {
				holdThread = new Thread(new Runnable() {
					@Override
					public void run() {
						logger.info("{} started", Thread.currentThread().getName());
						while (!Thread.currentThread().isInterrupted()) {
							try {
								logger.debug("{}", memcachedClient.getStats());
								Thread.sleep(10000);
							} catch (InterruptedException e) {
							} catch (Exception e) {
							}
						}
					}
				}, "Memcached-Holder");
				holdThread.start();
			}
		}
	}
}