package com.zee.controller;

import com.zee.dto.AddressDTO;
import com.zee.dto.ResponseWrapper;
import com.zee.entity.Address;
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


    /*
     Endpoint: /api/v1/address/{addressNo}
     HTTP Status Code: 200

     JSON Response Body:
     "success": true
     "message": "Address <addressNo> is successfully retrieved."
     "code":200
     "data":<address data>
    */
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

    /*
      Endpoint: /api/v1/address/{addressNo}

      JSON Response Body:
      <updated address data>
     */
    @PutMapping("{addressNo}")
    public ResponseEntity<ResponseWrapper> updateAddressNo(@PathVariable("addressNo") String addressNo, @RequestBody AddressDTO address) {

        return ResponseEntity.ok(new ResponseWrapper("successfully updated", addressService.update(addressNo, address)));

    }
}
