package com.yst.util;

import org.apache.commons.lang.StringUtils;
import com.caucho.hessian.client.HessianProxyFactory;

public class HessianRemoteCall {
	private static final long TIME_OUT =  Configure.getLong("hessian.timeout");// 120s改为1200s

	public static Object getRemoteObject(String url, Class<?> clazz,
			Object... objects) throws Exception {
		HessianProxyFactory factory = new HessianProxyFactory();
		factory.setReadTimeout(getTimeOut(objects));
		if (StringUtils.isEmpty(url))
			throw new Exception("远程Object接口[" + clazz + "]的url地址是空");
		Object object = null;
		try {
			object = factory.create(clazz, url);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Hessian寻找对象失败" + t.getMessage());
		}
		return object;
	}

	private static long getTimeOut(Object[] objects) {
		if (null == objects) {
			return TIME_OUT;
		}
		if (objects.length == 0 || null == objects[0]) {
			return TIME_OUT;
		}
		return parseLong(objects[0].toString(), TIME_OUT);

	}

	private static long parseLong(String obj, long defaultValue) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
			return defaultValue;
		}
	}
}
