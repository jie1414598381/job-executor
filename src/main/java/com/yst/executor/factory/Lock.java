package com.yst.executor.factory;

import java.util.HashMap;
import java.util.Map;

public class Lock {

	private static Map<String, Object> locks = new HashMap<String, Object>();

	public static Object getLock(String userCode) {
		if (!locks.containsKey(userCode)) {
			locks.put(userCode, new Object());
		}
		return locks.get(userCode);
	}
}
