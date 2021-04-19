package romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Employee;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.BadRequestException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.InternalServerErrorException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.repository.EmployeeRepository;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.ServiceCrud;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto.EmployeeDTO;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.form.EmployeeForm;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.mapper.MapperInterface;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements ServiceCrud<EmployeeDTO, EmployeeForm> {

    private final EmployeeRepository employeeRepository;
    private final MapperInterface<Employee, EmployeeDTO, EmployeeForm> employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, MapperInterface<Employee, EmployeeDTO, EmployeeForm> employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDTO save(EmployeeForm form, Long id) {
        employeeRepository.findByName(form.getName())
                .orElseThrow(() -> new BadRequestException("Funcionário já cadastrado com esse nome!"));

        Employee employee;

        try {
            employee = employeeRepository.save(getEmployee(form, id));
        } catch (Exception e) {
            throw new InternalServerErrorException("Não foi possível salvar o Funcionário!");
        }

        try {
            return employeeMapper.toDto(employee);
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível realizar o Mapper para DTO!");
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
    public List<EmployeeDTO> findAll() {
        try {
            return employeeMapper.toDto(employeeRepository.findAll());
        } catch (Exception e) {
            throw new InternalServerErrorException("Houve algum problema ao trazer todos os registros");
        }
    }

    public Employee getEmployee(EmployeeForm form, Long id) {
        try {
            Employee employee;

            employee = employeeMapper.toEntity(form);

            if (id != null) {
                employee.setId(id);
            }

            return employee;
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível realizar o Mapper para entidade!");
        }
    }
}
