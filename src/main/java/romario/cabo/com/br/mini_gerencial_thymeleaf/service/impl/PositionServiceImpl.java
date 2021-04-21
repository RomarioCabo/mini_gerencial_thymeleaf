package romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Position;
import romario.cabo.com.br.mini_gerencial_thymeleaf.repository.PositionRepository;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.ServiceCrud;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PositionServiceImpl implements ServiceCrud<Position> {

	private final PositionRepository positionRepository;

	public PositionServiceImpl(PositionRepository positionRepository) {
		this.positionRepository = positionRepository;
	}

	@Override
	public void save(Position form) {
		positionRepository.save(form);
	}

	@Override
	public void delete(Long id) {
		positionRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Position> findAll() {
		try {
			return positionRepository.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Position findById(Long id) {
		try {
			return positionRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean positionAlreadyExists(String name) {
		Optional<Position> positionOptional = positionRepository.findByName(name);

		return positionOptional.isPresent();
	}

	public boolean positionAlreadyExists(Long id) {
		Optional<Position> positionOptional = positionRepository.findById(id);

		return positionOptional.isPresent();
	}
	
	public boolean positionHasLinkedEmployees(Long id) {
		Optional<Position> positionOptional = positionRepository.findById(id);

		return !positionOptional.get().getEmployees().isEmpty();
	}
}
