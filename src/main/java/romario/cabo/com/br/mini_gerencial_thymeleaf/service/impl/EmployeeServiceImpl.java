package romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Employee;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.BadRequestException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.InternalServerErrorException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.repository.EmployeeRepository;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.ServiceCrud;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements ServiceCrud<Employee> {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public void save(Employee form) {
		employeeRepository.findByName(form.getName())
				.orElseThrow(() -> new BadRequestException("Funcionário já cadastrado com esse nome!"));

		try {
			employeeRepository.save(form);
		} catch (Exception e) {
			throw new InternalServerErrorException("Não foi possível salvar o Funcionário!");
		}
	}

	@Override
	public void delete(Long id) {
		employeeRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("Funcionário não localizado em nossa base de dados!"));

		try {
			employeeRepository.deleteById(id);
		} catch (Exception e) {
			throw new InternalServerErrorException("Não foi possível excluir o registro!");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> findAll() {
		try {
			return employeeRepository.findAll();
		} catch (Exception e) {
			throw new InternalServerErrorException("Houve algum problema ao trazer todos os registros");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Employee findById(Long id) {
		try {
			return employeeRepository.findEmployeeById(id);
		} catch (Exception e) {
			throw new InternalServerErrorException("Houve algum problema ao trazer o registro!");
		}
	}
}
