package com.test.ServiceYaml;

import java.util.ArrayList;
import java.util.List;

public class ServObj {
	/** Название службы */
	private String name;
	/** Родительская служба для други служб */
	private List<ServObj> parentServices;
	/** Зависит от служб */
	private List<ServObj> depServices;
	/** Служба запущена? */
	private boolean started;

	public ServObj(String name) {
		this.started = false;
		this.name = name;
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
}
