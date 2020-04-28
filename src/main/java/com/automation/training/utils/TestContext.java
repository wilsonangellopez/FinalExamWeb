package com.automation.training.utils;

import static java.lang.ThreadLocal.withInitial;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wil
 */
public enum TestContext {
	
CONTEXT;
	
	private final ThreadLocal<Map<String, Object>> testContexts = withInitial(HashMap::new);

	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		return (T) testContexts.get().get(name);
	}

	public <T> T set(String name, T object) {
		testContexts.get().put(name, object);
		return object;
	}

}
