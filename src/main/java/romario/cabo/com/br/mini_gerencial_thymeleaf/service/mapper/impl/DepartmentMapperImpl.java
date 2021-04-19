package romario.cabo.com.br.mini_gerencial_thymeleaf.service.mapper.impl;

import org.springframework.stereotype.Component;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Department;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto.DepartmentDTO;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.form.DepartmentForm;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.mapper.MapperInterface;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentMapperImpl implements MapperInterface<Department, DepartmentDTO, DepartmentForm> {

    @Override
    public Department toEntity(DepartmentForm departmentForm) {
        return getDepartment(departmentForm);
    }

    @Override
    public List<Department> toEntity(List<DepartmentForm> departmentForms) {
        List<Department> departments = new ArrayList<>();

        departmentForms.forEach(obj -> departments.add(getDepartment(obj)));

        return departments;
    }

    @Override
    public DepartmentDTO toDto(Department department) {
        return getDepartment(department);
    }

    @Override
    public List<DepartmentDTO> toDto(List<Department> departments) {
        List<DepartmentDTO> departmentsDto = new ArrayList<>();

        departments.forEach(obj -> departmentsDto.add(getDepartment(obj)));

        return departmentsDto;
    }

    @Override
    public List<DepartmentDTO> tupleToDto(List<Tuple> tuples) {
        List<DepartmentDTO> departmentsDto = new ArrayList<>();

        tuples.forEach(obj -> departmentsDto.add(getDepartment(obj)));

        return departmentsDto;
    }

    private Department getDepartment(DepartmentForm departmentForm) {
        Department department = new Department();
        department.setId(null);
        department.setName(departmentForm.getName());

        return department;
    }

    private DepartmentDTO getDepartment(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());

        return departmentDTO;
    }

    private DepartmentDTO getDepartment(Tuple tuple) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId((Long) tuple.get(0));
        departmentDTO.setName((String) tuple.get(1));

        return departmentDTO;
    }
}
