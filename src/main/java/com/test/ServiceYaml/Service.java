package com.test.ServiceYaml;

import java.util.List;

public class Service {
	String service;
	List<String> dependsOn;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public List<String> getDependsOn() {
		return dependsOn;
	}

	public void setDependsOn(List<String> dependsOn) {
		this.dependsOn = dependsOn;
	}
}
