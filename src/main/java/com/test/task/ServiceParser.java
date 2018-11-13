package com.test.task;

import com.test.ServiceYaml.ServObj;
import com.test.ServiceYaml.ServiceData;
import com.test.ServiceYaml.ServiceProperties;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class ServiceParser {

	private ServiceData _data;
	private ServiceProperties _svcProps;

	public static void main(String[] args) {
		ServiceParser _svc = new ServiceParser();
	}

	public ServiceParser() {
		_data = new ServiceData();
		_svcProps = parseServices();
		printSvcDependencyTree(_svcProps);
		genServiceObjects(_svcProps);
		_data.printData();
	}

	/** Генерация объектов {@link com.test.ServiceYaml.ServObj} из их описания */
	private void genServiceObjects(ServiceProperties _svcProps) {
		_svcProps.getServices().stream().forEach(service -> {
			// Заполнение данных родителького или корневого объекта
			ServObj parentSvcObj = _data.getServObj(service.getService());

			// Заполнение данных дочерних объектов, если таковые имеются
			if (service.getDependsOn() != null)
				service.getDependsOn().stream().forEach(depSvcName -> {
					// Прописываем ссылку на дочерний объект родительскому объекту
					parentSvcObj.addDepService(_data.getServObj(depSvcName));

					// Получение дочернего объекта и заполнение ссылок
					ServObj childSvcObj = _data.getServObj(depSvcName);
					childSvcObj.addParentService(parentSvcObj);
				});
		});
	}

	/** Вывод дерева служб и их зависимостей */
	private void printSvcDependencyTree(ServiceProperties _svcProps) {
		_svcProps.getServices().stream().forEach(service -> {
			System.out.println(service.getService());
			if (service.getDependsOn() != null)
				service.getDependsOn().stream().forEach(dep -> System.out.println("\t" + dep));
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
}
