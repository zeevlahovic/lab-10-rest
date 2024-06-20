package com.zee.controller;

import com.zee.dto.ResponseWrapper;
import com.zee.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/address/{addressNo}")
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
    @GetMapping
    public ResponseEntity<ResponseWrapper> retrieveAddress(@PathVariable("addressNo") String addressNo) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseWrapper("Address " + addressNo + "is successfully retrieved.",addressService.findByAddressNo(addressNo)));

    }
    /*
      Endpoint: /api/v1/address/{addressNo}

      JSON Response Body:
      <updated address data>
     */

}
