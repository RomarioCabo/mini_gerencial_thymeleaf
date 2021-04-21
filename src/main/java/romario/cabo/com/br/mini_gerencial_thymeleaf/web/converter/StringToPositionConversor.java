package romario.cabo.com.br.mini_gerencial_thymeleaf.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Position;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl.PositionServiceImpl;


@Component
public class StringToPositionConversor implements Converter<String, Position> {
	
	private final PositionServiceImpl service;
	
	StringToPositionConversor(PositionServiceImpl service) {
		this.service = service;
	}
	
	@Override
	public Position convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.findById(id);
	}
}
