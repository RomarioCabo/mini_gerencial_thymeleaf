package romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Position;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.BadRequestException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.InternalServerErrorException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.repository.PositionRepository;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.ServiceCrud;

import java.util.List;

@Service
@Transactional
public class PositionServiceImpl implements ServiceCrud<Position> {

    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }
    
    @Override
    public void save(Position form) {
        positionRepository.findByName(form.getName())
                .orElseThrow(() -> new BadRequestException("Funcionário já cadastrado com esse nome!"));

        try {
            positionRepository.save(form);
        } catch (Exception e) {
            throw new InternalServerErrorException("Não foi possível salvar o Cargo!");
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
    public List<Position> findAll() {
        try {
            return positionRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException("Houve algum problema ao trazer todos os registros");
        }
    }

    @Override
    public Position findById(Long id) {
        try {
            return positionRepository.findPositionById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Houve algum problema ao trazer o registro!");
        }
    }
}
