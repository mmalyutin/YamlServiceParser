package com.test.ServiceYaml;

import java.util.ArrayList;
import java.util.List;

public class ServObj {
	/** Название службы */
	private String name;
	/** Родительские службы */
	private List<ServObj> parentServices;
	/** Зависит от служб */
	private List<ServObj> depServices;

	public ServObj(String name) {
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
}
