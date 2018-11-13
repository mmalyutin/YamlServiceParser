package com.test.task;

import com.test.ServiceYaml.ServiceProperties;
import com.test.customer.Customer;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class ServiceParser {
	public static void main(String[] args) {
		ServiceParser _svc = new ServiceParser();
		ServiceProperties services = _svc.parseServices();

		printSvcDepedencyTree(services);

//		_svc.parseServices();
//		_svc.parseCustomer();
	}

	/** Вывод дерева служб и их зависимостей */
	private static void printSvcDepedencyTree(ServiceProperties services) {
		services.getServices().stream().forEach(service -> {
			System.out.println(service.getService());
			if (service.getDependsOn() != null)
				service.getDependsOn().stream().forEach(dep -> System.out.println("\t" + dep.toString()));
		});
	}

	// Загрузка данных из services.yaml и формирование структуры Services
	private ServiceProperties parseServices() {
		Yaml yaml = new Yaml(new Constructor(ServiceProperties.class));
		InputStream inputStream = this.getClass()
				.getClassLoader()
				.getResourceAsStream("data/services.yaml");
		ServiceProperties services = yaml.load(inputStream);

		return services;
	}

	/** Пример парсинга и мэппинга YAML */
	private void parseCustomer() {
		Yaml yaml = new Yaml(new Constructor(Customer.class));
		InputStream inputStream = this.getClass()
				.getClassLoader()
				.getResourceAsStream("data/customer_with_contact_details_and_address.yaml");
		Customer customer = yaml.load(inputStream);

		customer.getContactDetails().stream().forEach(contact -> System.out.println(contact.getNumber() + " : " + contact.getType()));
	}
}
