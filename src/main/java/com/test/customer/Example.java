package com.test.customer;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class Example {
	public Example() {
		Example example = new Example();
		example.parseCustomer();
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
