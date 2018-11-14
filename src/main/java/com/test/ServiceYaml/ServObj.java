package com.test.ServiceYaml;

import java.util.ArrayList;
import java.util.List;

public class ServObj implements Comparable<ServObj> {
	/** Название службы */
	private String name;
	/** Родительская служба для други служб */
	private List<ServObj> parentServices;
	/** Зависит от служб */
	private List<ServObj> depServices;
	/** Служба запущена? */
	private boolean started;
	/** Уровень вершины в графе */
	private Integer nodeLevel;

	public ServObj(String name) {
		this.started = false;
		this.name = name;
		this.nodeLevel = null;
		this.parentServices = null;
		this.depServices = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ServObj> getParentServices() {
		return parentServices;
	}

	public void addParentService(ServObj service) {
		if (parentServices == null)
			parentServices = new ArrayList<ServObj>();

		parentServices.add(service);
	}

	public List<ServObj> getDepServices() {
		return depServices;
	}

	public void addDepService(ServObj service) {
		if (depServices == null)
			depServices = new ArrayList<ServObj>();

		depServices.add(service);
	}

	/** Служба уже запущена? */
	public boolean serviceStarted() {
		return this.started;
	}

	/** Запустить службу */
	public void runService() {
		if (!this.started) {
			this.started = true;
		}
	}

	// Запущены ли службы, от которых зависит текущая?
	public boolean isDependendServicesStarted() {
		// Если зависимых служб нет, считаем что они запущены
		if (depServices == null)
			return true;

		for (ServObj depObj : depServices) {
			if (!depObj.serviceStarted())
				return false;
		}

		return true;
	}

	/** Возврат веса вершины в графе */
	public Integer getNodeLevel() {
		// Если уровень вершины ещё небыл вычислен, то вычисляем его
		if (nodeLevel == null)
			nodeLevel = calculateNodeLevel(0, this.depServices);

		return nodeLevel;
	}

	/** Вычисление уровня вершины в графе */
	private Integer calculateNodeLevel(Integer initNodeLvl, List<ServObj> childNodes) {
		Integer curNodeLevel = initNodeLvl + 1;

		// Дошли до конечного узла или узел автономен
		if (childNodes == null)
			return curNodeLevel;

		// Обход дочерних узлов
		Integer nestedNodeLevel;
		for (ServObj obj : childNodes) {
			// Получение уровня дочернего узла
			nestedNodeLevel = calculateNodeLevel(curNodeLevel, obj.getDepServices());
			// Определение уровня максимальной вложенности
			curNodeLevel = Math.max(curNodeLevel, nestedNodeLevel);
		}

		return curNodeLevel;
	}

	@Override
	public int compareTo(ServObj o) {
		return this.getNodeLevel().compareTo(o.getNodeLevel());
	}
}
