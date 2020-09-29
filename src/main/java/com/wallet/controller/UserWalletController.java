package com.wallet.controller;


import com.wallet.dto.UserWalletDTO;
import com.wallet.entity.UserWallet;
import com.wallet.response.Response;
import com.wallet.service.impl.UserWalletServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "user-wallet")
public class UserWalletController {

    @Autowired
    UserWalletServiceImpl service;

    @PostMapping
    public ResponseEntity<Response<UserWalletDTO>> create(@Valid @RequestBody UserWalletDTO dto, BindingResult result){

        Response<UserWalletDTO> response = new Response<UserWalletDTO>();

        if(result.hasErrors()){
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
        }

        UserWallet uw = service.save(UserWalletDTO.fromDTO(dto));;

        response.setData(UserWalletDTO.toDTO(uw));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
