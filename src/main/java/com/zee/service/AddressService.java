package com.zee.service;

import com.zee.dto.AddressDTO;

public interface AddressService {

    AddressDTO findByAddressNo(String addressNo);

    AddressDTO update(String addressNo, AddressDTO addressDTO) ;

}
