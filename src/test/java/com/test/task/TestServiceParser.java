package com.test.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.test.ServiceYaml.ServObj;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TestServiceParser {

	@Test
	public void testSequatialStartingServeiceOrder() {
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

	@Test
	public void testParallelStartingServeiceOrder() {
		ServiceParser parser = new ServiceParser();

		// Фактический порядок запуска
		String factOrder = "";
		List<ServObj> _startedOrder = parser.parse();

		// Вывод списка служб для параллельного запуска
		ArrayList<ArrayList<ServObj>> parallelStarting = getParralelStartingOrder(_startedOrder);
		parallelStarting.forEach(services -> {
			System.out.println("Starting parallel:");
			services.forEach(servObj -> System.out.println("\t" + servObj.getName()));
		});
	}

	/** Возврат списка служб для параллельного запуска */
	public ArrayList<ArrayList<ServObj>> getParralelStartingOrder(List<ServObj> _nodes) {
		ArrayList<ArrayList<ServObj>> resutlLst = new ArrayList<ArrayList<ServObj>>();

		Integer curStartLvl = 0;
		ArrayList<ServObj> parallelOrder = null;

		for (ServObj obj : _nodes) {
			if (obj.getNodeLevel() == curStartLvl)
				// Помещаем вершинку в список, в продолжение параллельного запуска
				parallelOrder.add(obj);
			else {
				// Смена списка параллельного запуска
				parallelOrder = new ArrayList<ServObj>();
				resutlLst.add(parallelOrder);

				// Формирование нового списка на параллельный запуск и помещение внего службы (узла)
				curStartLvl = obj.getNodeLevel();
				parallelOrder.add(obj);
			}
		}

		return resutlLst;
	}
}
