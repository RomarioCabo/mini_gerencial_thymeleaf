package romario.cabo.com.br.mini_gerencial_thymeleaf.service.mapper.impl;

import org.springframework.stereotype.Component;

import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Address;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Employee;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Position;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto.AddressDTO;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto.EmployeeDTO;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto.PositionDTO;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.form.AddressForm;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.form.EmployeeForm;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.form.PositionForm;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.mapper.MapperInterface;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapperImpl implements MapperInterface<Employee, EmployeeDTO, EmployeeForm> {

    private final MapperInterface<Address, AddressDTO, AddressForm> addressMapper;
    private final MapperInterface<Position, PositionDTO, PositionForm> positionMapper;

    public EmployeeMapperImpl(MapperInterface<Address, AddressDTO, AddressForm> addressMapper, MapperInterface<Position, PositionDTO, PositionForm> positionMapper) {
        this.addressMapper = addressMapper;
        this.positionMapper = positionMapper;
    }

    @Override
    public Employee toEntity(EmployeeForm employeeForm) {
        return getEmployee(employeeForm);
    }

    @Override
    public List<Employee> toEntity(List<EmployeeForm> employeeForms) {
        List<Employee> employees = new ArrayList<>();

        employeeForms.forEach(obj -> employees.add(getEmployee(obj)));

        return employees;
    }

    @Override
    public EmployeeDTO toDto(Employee employee) {
        return getEmployee(employee);
    }

    @Override
    public List<EmployeeDTO> toDto(List<Employee> employees) {
        List<EmployeeDTO> employeesDTO = new ArrayList<>();

        employees.forEach(obj -> employeesDTO.add(getEmployee(obj)));

        return employeesDTO;
    }

    @Override
    public List<EmployeeDTO> tupleToDto(List<Tuple> tuples) {
        List<EmployeeDTO> employeesDTO = new ArrayList<>();

        tuples.forEach(obj -> employeesDTO.add(getEmployee(obj)));

        return employeesDTO;
    }

    private Employee getEmployee(EmployeeForm employeeForm) {
        Employee employee = new Employee();
        employee.setId(null);
        employee.setName(employeeForm.getName());
        employee.setSalary(employeeForm.getSalary());
        employee.setDateEntry(employeeForm.getDateEntry());
        employee.setDepartureDate(employeeForm.getDepartureDate());
        employee.setAddress(getAddress(employeeForm.getIdAddress()));
        employee.setPosition(getPosition(employeeForm.getIdPosition()));

        return employee;
    }

    private EmployeeDTO getEmployee(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setDateEntry(employee.getDateEntry());
        employeeDTO.setDepartureDate(employee.getDepartureDate());
        employeeDTO.setAddress(addressMapper.toDto(employee.getAddress()));
        employeeDTO.setPosition(positionMapper.toDto(employee.getPosition()));

        return employeeDTO;
    }

    private EmployeeDTO getEmployee(Tuple tuple) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId((Long) tuple.get(0));
        employeeDTO.setName((String) tuple.get(1));
        employeeDTO.setSalary((Double) tuple.get(2));
        employeeDTO.setDateEntry((LocalDate) tuple.get(3));
        employeeDTO.setDepartureDate((LocalDate) tuple.get(4));
        employeeDTO.setAddress(addressMapper.toDto((Address) tuple.get(5)));
        employeeDTO.setPosition(positionMapper.toDto((Position) tuple.get(6)));

        return employeeDTO;
    }

    private Address getAddress(Long idAddress) {
        Address address = new Address();
        address.setId(idAddress);
        address.setPublicPlace(null);
        address.setNeighborhood(null);
        address.setCity(null);
        address.setUf(null);
        address.setCep(null);
        address.setNumber(null);
        address.setComplement(null);

        return address;
    }

    private Position getPosition(Long idPosition) {
        Position position = new Position();
        position.setId(idPosition);
        position.setName(null);
        position.setDepartment(null);

        return position;
    }
}
