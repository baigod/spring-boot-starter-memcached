package com.szzc.springboot.memcached.utils;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

public class MemcachedLockUtils {

	@Autowired
	private MemcachedClient memcachedClient;

	/**
	 * 上锁，返回是否上锁成功
	 * 
	 * @param lock
	 * @param exp
	 * @return
	 */
	public boolean lock(String lock, int exp) {
		OperationFuture<Boolean> add = this.memcachedClient.add(lock, exp, System.currentTimeMillis());
		try {
			return add.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 解锁
	 * 
	 * @param lock
	 * @param exp
	 * @return
	 */
	public void unlock(String lock) {
		this.memcachedClient.delete(lock);
	}

}
