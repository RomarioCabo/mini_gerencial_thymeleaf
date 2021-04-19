package romario.cabo.com.br.mini_gerencial_thymeleaf.service.mapper.impl;

import org.springframework.stereotype.Component;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Department;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Position;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto.DepartmentDTO;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto.PositionDTO;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.form.DepartmentForm;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.form.PositionForm;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.mapper.MapperInterface;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Component
public class PositionMapperImp implements MapperInterface<Position, PositionDTO, PositionForm> {

    private final MapperInterface<Department, DepartmentDTO, DepartmentForm> departmentMapper;

    public PositionMapperImp(MapperInterface<Department, DepartmentDTO, DepartmentForm> departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public Position toEntity(PositionForm positionForm) {
        return getPosition(positionForm);
    }

    @Override
    public List<Position> toEntity(List<PositionForm> positionForms) {
        List<Position> positions = new ArrayList<>();

        positionForms.forEach(obj -> positions.add(getPosition(obj)));

        return positions;
    }

    @Override
    public PositionDTO toDto(Position position) {
        return getPosition(position);
    }

    @Override
    public List<PositionDTO> toDto(List<Position> positions) {
        List<PositionDTO> positionsDTO = new ArrayList<>();

        positions.forEach(obj -> positionsDTO.add(getPosition(obj)));

        return positionsDTO;
    }

    @Override
    public List<PositionDTO> tupleToDto(List<Tuple> tuples) {
        List<PositionDTO> positionsDTO = new ArrayList<>();

        tuples.forEach(obj -> positionsDTO.add(getPosition(obj)));

        return positionsDTO;
    }

    private Position getPosition(PositionForm positionForm) {
        Position position = new Position();
        position.setId(null);
        position.setName(positionForm.getName());
        position.setDepartment(getDepartment(positionForm.getIdDepartment()));

        return position;
    }

    private PositionDTO getPosition(Position position) {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setId(position.getId());
        positionDTO.setDepartment(departmentMapper.toDto(position.getDepartment()));

        return positionDTO;
    }

    private PositionDTO getPosition(Tuple tuple) {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setId((Long) tuple.get(0));
        positionDTO.setDepartment(departmentMapper.toDto((Department) tuple.get(1)));

        return positionDTO;
    }

    private Department getDepartment(Long idDepartment) {
        Department department = new Department();
        department.setId(idDepartment);
        department.setName(null);

        return department;
    }
}
