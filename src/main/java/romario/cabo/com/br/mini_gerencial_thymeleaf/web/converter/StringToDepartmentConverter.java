package romario.cabo.com.br.mini_gerencial_thymeleaf.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Department;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl.DepartmentServiceImpl;

@Component
public class StringToDepartmentConverter implements Converter<String, Department> {
	
	private final DepartmentServiceImpl service;
	
	StringToDepartmentConverter(DepartmentServiceImpl service) {
		this.service = service;
	}

	@Override
	public Department convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.findById(id);
	}

}
