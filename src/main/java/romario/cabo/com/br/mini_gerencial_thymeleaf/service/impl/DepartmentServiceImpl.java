package romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl;

import org.springframework.stereotype.Service;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Department;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.BadRequestException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.InternalServerErrorException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.repository.DepartmentRepository;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.ServiceCrud;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto.DepartmentDTO;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.form.DepartmentForm;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.mapper.MapperInterface;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements ServiceCrud<DepartmentDTO, DepartmentForm> {

    private final DepartmentRepository departmentRepository;
    private final MapperInterface<Department, DepartmentDTO, DepartmentForm> departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, MapperInterface<Department, DepartmentDTO, DepartmentForm> departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public DepartmentDTO save(DepartmentForm form, Long id) {
        departmentRepository
                .findByName(form.getName()).orElseThrow(() -> new BadRequestException("Departamento já cadastrado em nossa base de dados"));


        Department department;

        try {
            department = departmentRepository.save(getDepartment(form, id));
        } catch (Exception e) {
            throw new InternalServerErrorException("Não foi possível salvar o Departamento!");
        }

        try {
            return departmentMapper.toDto(department);
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível realizar o Mapper para DTO!");
        }
    }

    @Override
    public void delete(Long id) {
        departmentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Departamento não localizado em nossa base de dados!"));

        try {
            departmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Não foi possível excluir o registro!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDTO> findAll() {
        try {
            return departmentMapper.toDto(departmentRepository.findAll());
        } catch (Exception e) {
            throw new InternalServerErrorException("Houve algum problema ao trazer todos os registros");
        }
    }

    public Department getDepartment(DepartmentForm form, Long id) {
        try {
            Department department;

            department = departmentMapper.toEntity(form);

            if (id != null) {
                department.setId(id);
            }

            return department;
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível realizar o Mapper para entidade!");
        }
    }
}
