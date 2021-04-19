package romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Position;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.BadRequestException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.InternalServerErrorException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.repository.PositionRepository;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.ServiceCrud;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto.PositionDTO;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.form.PositionForm;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.mapper.MapperInterface;

import java.util.List;

@Service
@Transactional
public class PositionServiceImpl implements ServiceCrud<PositionDTO, PositionForm> {

    private final PositionRepository positionRepository;
    private final MapperInterface<Position, PositionDTO, PositionForm> positionMapper;

    public PositionServiceImpl(PositionRepository positionRepository, MapperInterface<Position, PositionDTO, PositionForm> positionMapper) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
    }

    @Override
    public PositionDTO save(PositionForm form, Long id) {
        positionRepository.findByName(form.getName())
                .orElseThrow(() -> new BadRequestException("Funcionário já cadastrado com esse nome!"));

        Position position;

        try {
            position = positionRepository.save(getPosition(form, id));
        } catch (Exception e) {
            throw new InternalServerErrorException("Não foi possível salvar o Cargo!");
        }

        try {
            return positionMapper.toDto(position);
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível realizar o Mapper para DTO!");
        }
    }

    @Override
    public void delete(Long id) {
        positionRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Cargo não localizado em nossa base de dados!"));

        try {
            positionRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Não foi possível excluir o registro!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PositionDTO> findAll() {
        try {
            return positionMapper.toDto(positionRepository.findAll());
        } catch (Exception e) {
            throw new InternalServerErrorException("Houve algum problema ao trazer todos os registros");
        }
    }

    public Position getPosition(PositionForm form, Long id) {
        try {
            Position position;

            position = positionMapper.toEntity(form);

            if (id != null) {
                position.setId(id);
            }

            return position;
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível realizar o Mapper para entidade!");
        }
    }
}
