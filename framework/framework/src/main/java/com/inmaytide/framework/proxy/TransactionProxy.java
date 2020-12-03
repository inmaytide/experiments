package com.inmaytide.framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inmaytide.framework.annotation.Transaction;
import com.inmaytide.framework.helper.DatabaseHelper;

public class TransactionProxy implements Proxy {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionProxy.class);
	
	private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>() {
		@Override
		protected Boolean initialValue() {
			return false;
		}
	};

	@Override
	public Object doProxy(ProxyChain chain) throws Throwable {
		Object result;
		boolean flag = FLAG_HOLDER.get();
		Method method = chain.getTargetMethod();
		if (!flag && method.isAnnotationPresent(Transaction.class)) {
			FLAG_HOLDER.set(true);
			try {
				DatabaseHelper.beginTransaction();
				logger.debug("begin transaction");
				result = chain.doProxyChain();
				DatabaseHelper.commitTransaction();
				logger.debug("commit transaction");
			} catch (Exception e) {
				DatabaseHelper.rollbackTransaction();
				logger.debug("rollback transaction");
				throw e;
			}
		} else {
			result = chain.doProxyChain();
		}
		return result;
	}

}
