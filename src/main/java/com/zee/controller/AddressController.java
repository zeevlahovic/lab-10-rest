package com.zee.controller;

import com.zee.dto.AddressDTO;
import com.zee.dto.ResponseWrapper;
import com.zee.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }



    @GetMapping("{addressNo}")
    public ResponseEntity<ResponseWrapper> retrieveAddress(@PathVariable("addressNo") String addressNo) {

        AddressDTO foundAddress = addressService.findByAddressNo(addressNo);
        ResponseWrapper responseWrapper = ResponseWrapper.builder()
                .success(true)
                .message("Address " + addressNo + " is succesfully retrived")
                .code(HttpStatus.OK.value())
                .data(foundAddress).build();
        return ResponseEntity.ok(responseWrapper);
    }


    @PutMapping("{addressNo}")
    public AddressDTO updateAddress(@PathVariable("addressNo")String addressNo,@RequestBody AddressDTO addressDTO){

        return addressService.update(addressNo,addressDTO);
    }
}
