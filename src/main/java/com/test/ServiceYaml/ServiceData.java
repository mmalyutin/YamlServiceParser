package com.test.ServiceYaml;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/** Хранилище объектов Service */
public class ServiceData {
	private Map<String, ServObj> _data;

	public ServiceData() {
		_data = new HashMap<String, ServObj>();
	}

	/** Возврат объекта {@link ServObj} по его имени из хранилища. Если объекта в хранилище не существует,
	 * то он создаётся и возвращается */
	public ServObj getServObj(String name) {
		ServObj obj = _data.get(name);
		if (obj == null) {
			obj = new ServObj(name);
			_data.put(name, obj);
		}

		return  obj;
	}

	/** Вывод данных об объектах {@link ServObj} */
	public void printData() {
		_data.forEach((serviceName, servObj) -> {

			String depObjects = "";
			if (servObj.getDepServices() != null) {
				for (ServObj dep : servObj.getDepServices()) {
					// Первый проход
					if (depObjects.equals(""))
						depObjects = dep.getName();
					else
						depObjects = depObjects + ", " + dep.getName();
				}
			}

			String parObjects = "";
			if (servObj.getParentServices() != null) {
				for (ServObj par : servObj.getParentServices()) {
					// Первый проход
					if (parObjects.equals(""))
						parObjects = par.getName();
					else
						parObjects = parObjects + ", " + par.getName();
				}
			}

			String result = "Service name: " + serviceName;
			if (!parObjects.equals(""))
				result += ", parent objects = " + parObjects;
			if (!depObjects.equals(""))
				result += ", dependent objects = " + depObjects;

			System.out.println(result);
		});
	}
}