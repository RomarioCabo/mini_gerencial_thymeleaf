package romario.cabo.com.br.mini_gerencial_thymeleaf.service.mapper.impl;

import org.springframework.stereotype.Component;

import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Address;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.enums.UF;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.dto.AddressDTO;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.form.AddressForm;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.mapper.MapperInterface;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddressMapperImpl implements MapperInterface<Address, AddressDTO, AddressForm> {

    @Override
    public Address toEntity(AddressForm addressForm) {
        return getAddress(addressForm);
    }

    @Override
    public List<Address> toEntity(List<AddressForm> addressForms) {
        List<Address> addresses = new ArrayList<>();

        addressForms.forEach(obj -> addresses.add(getAddress(obj)));

        return addresses;
    }

    @Override
    public AddressDTO toDto(Address address) {
        return getAddress(address);
    }

    @Override
    public List<AddressDTO> toDto(List<Address> addresses) {
        List<AddressDTO> addressesDTO = new ArrayList<>();

        addresses.forEach(obj -> addressesDTO.add(getAddress(obj)));

        return addressesDTO;
    }

    @Override
    public List<AddressDTO> tupleToDto(List<Tuple> tuples) {
        List<AddressDTO> addressesDTO = new ArrayList<>();

        tuples.forEach(obj -> addressesDTO.add(getAddress(obj)));

        return addressesDTO;
    }

    private Address getAddress(AddressForm addressForm) {
        Address address = new Address();
        address.setId(null);
        address.setPublicPlace(addressForm.getPublicPlace());
        address.setNeighborhood(addressForm.getNeighborhood());
        address.setCity(addressForm.getCity());
        address.setUf(UF.toEnum(addressForm.getCep()));
        address.setCep(addressForm.getCep());
        address.setNumber(addressForm.getNumber());
        address.setComplement(address.getComplement());

        return address;
    }

    private AddressDTO getAddress(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setPublicPlace(address.getPublicPlace());
        addressDTO.setNeighborhood(address.getNeighborhood());
        addressDTO.setCity(address.getCity());
        addressDTO.setUf(address.getUf().getInitials());
        addressDTO.setCep(address.getCep());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setComplement(address.getComplement());

        return addressDTO;
    }

    private AddressDTO getAddress(Tuple tuple) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId((Long) tuple.get(0));
        addressDTO.setPublicPlace((String) tuple.get(1));
        addressDTO.setNeighborhood((String) tuple.get(2));
        addressDTO.setCity((String) tuple.get(3));
        addressDTO.setUf((String) tuple.get(4));
        addressDTO.setCep((String) tuple.get(5));
        addressDTO.setNumber((Integer) tuple.get(6));
        addressDTO.setComplement((String) tuple.get(7));

        return addressDTO;
    }
}
