package romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl;

import org.springframework.stereotype.Service;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Department;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.BadRequestException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.InternalServerErrorException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.repository.DepartmentRepository;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.ServiceCrud;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentServiceImpl implements ServiceCrud<Department> {

	private final DepartmentRepository departmentRepository;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public void save(Department form) {
		Optional<Department> departmentOptional = departmentRepository.findByName(form.getName());

		if (departmentOptional.isPresent()) {
			throw new BadRequestException("Departamento já cadastrado em nossa base de dados!");
		}

		try {
			departmentRepository.save(form);
		} catch (Exception e) {
			throw new InternalServerErrorException("Não foi possível salvar o Departamento!");
		}
	}

	@Override
	public void delete(Long id) {
		Optional<Department> departmentOptional = departmentRepository.findById(id);

		if (!departmentOptional.isPresent()) {
			throw new BadRequestException("Departamento não localizado em nossa base de dados!");
		}

		try {
			departmentRepository.deleteById(id);
		} catch (Exception e) {
			throw new InternalServerErrorException("Não foi possível excluir o registro!");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Department> findAll() {
		try {
			return departmentRepository.findAll();
		} catch (Exception e) {
			throw new InternalServerErrorException("Houve algum problema ao trazer todos os registros!");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Department findById(Long id) {
		try {
			return departmentRepository.findDepartmentById(id);
		} catch (Exception e) {
			throw new InternalServerErrorException("Houve algum problema ao trazer o registro!");
		}
	}
}
