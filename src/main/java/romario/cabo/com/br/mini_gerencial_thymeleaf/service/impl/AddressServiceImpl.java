package romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Address;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.BadRequestException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.exception.InternalServerErrorException;
import romario.cabo.com.br.mini_gerencial_thymeleaf.repository.AddressRepository;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.ServiceCrud;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto.AddressDTO;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.form.AddressForm;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.mapper.MapperInterface;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements ServiceCrud<AddressDTO, AddressForm> {

    private final AddressRepository addressRepository;
    private final MapperInterface<Address, AddressDTO, AddressForm> addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, MapperInterface<Address, AddressDTO, AddressForm> addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }


    @Override
    public AddressDTO save(AddressForm form, Long id) {
        Address address;

        try {
            address = addressRepository.save(getDepartment(form, id));
        } catch (Exception e) {
            throw new InternalServerErrorException("Não foi possível salvar o Endereço!");
        }

        try {
            return addressMapper.toDto(address);
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível realizar o Mapper para DTO!");
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
    public List<AddressDTO> findAll() {
        try {
            return addressMapper.toDto(addressRepository.findAll());
        } catch (Exception e) {
            throw new InternalServerErrorException("Houve algum problema ao trazer todos os registros");
        }
    }

    public Address getDepartment(AddressForm form, Long id) {
        try {
            Address address;

            address = addressMapper.toEntity(form);

            if (id != null) {
                address.setId(id);
            }

            return address;
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível realizar o Mapper para entidade!");
        }
    }
}
