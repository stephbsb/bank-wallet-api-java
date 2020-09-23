package com.wallet.controller;

import com.wallet.dto.WalletDTO;
import com.wallet.entity.Wallet;
import com.wallet.response.Response;
import com.wallet.service.WalletService;
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
@RequestMapping(value = "wallet")
public class WalletController {

    @Autowired
    WalletService service;

    @PostMapping
    public ResponseEntity<Response<WalletDTO>> create(@Valid @RequestBody WalletDTO dto, BindingResult result){

        Response<WalletDTO> response = new Response<WalletDTO>();

        if(result.hasErrors()){
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Wallet w = service.save(WalletDTO.fromDTO(dto));;

        response.setData(WalletDTO.toDTO(w));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
