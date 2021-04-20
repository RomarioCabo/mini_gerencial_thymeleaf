package romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Address;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.BadRequestException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.InternalServerErrorException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.repository.AddressRepository;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.ServiceCrud;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements ServiceCrud<Address> {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void save(Address form) {
        try {
            addressRepository.save(form);
        } catch (Exception e) {
            throw new InternalServerErrorException("Não foi possível salvar o Endereço!");
        }
    }

    @Override
    public void delete(Long id) {
        addressRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Endereço não localizado em nossa base de dados!"));

        try {
            addressRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Não foi possível excluir o registro!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findAll() {
        try {
            return addressRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException("Houve algum problema ao trazer todos os registros!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Address findById(Long id) {
        try {
            return addressRepository.findAddressById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Houve algum problema ao trazer todos os registros");
        }
    }
}
