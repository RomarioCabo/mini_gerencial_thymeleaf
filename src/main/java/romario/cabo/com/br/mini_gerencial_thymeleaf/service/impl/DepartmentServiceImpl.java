package romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl;

import org.springframework.stereotype.Service;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Department;
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
		departmentRepository.save(form);
	}

	@Override
	public void delete(Long id) {
		departmentRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Department> findAll() {
		try {
			return departmentRepository.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Department findById(Long id) {
		try {
			return departmentRepository.findDepartmentById(id);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean departmentAlreadyExists(String name) {
		Optional<Department> departmentOptional = departmentRepository.findByName(name);

		return departmentOptional.isPresent();
	}

	public boolean departmentAlreadyExists(Long id) {
		Optional<Department> departmentOptional = departmentRepository.findById(id);

		return departmentOptional.isPresent();
	}

	public boolean departmentHasLinkedPositions(Long id) {
		Optional<Department> departmentOptional = departmentRepository.findById(id);

		return !departmentOptional.get().getPositions().isEmpty();
	}
}
