package com.test.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.test.ServiceYaml.ServObj;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestServiceParser {

	@Test
	public void testStartingServeiceOrder() {
		ServiceParser parser = new ServiceParser();

		// Фактический порядок запуска
		String factOrder = "";
		List<ServObj> _startedOrder = parser.parse();
		for (ServObj obj : _startedOrder)
			factOrder += " ---> " + obj.getName();

		String waitOrder = "";
		List<ServObj> _waitingOrder = Arrays.asList(new ServObj("postgres"),
												   new ServObj("redis"),
				                                   new ServObj("service4"),
												   new ServObj("service3"),
												   new ServObj("service2"));

		for (ServObj obj : _waitingOrder)
			waitOrder += " ---> " + obj.getName();

		assertEquals(waitOrder, factOrder);
	}
}
