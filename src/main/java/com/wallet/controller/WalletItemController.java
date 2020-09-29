package com.wallet.controller;

import com.wallet.dto.WalletItemDTO;
import com.wallet.entity.WalletItem;
import com.wallet.response.Response;
import com.wallet.service.WalletItemService;
import com.wallet.util.enums.TypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("wallet-item")
public class WalletItemController {

    @Autowired
    private WalletItemService service;

    @PostMapping
    public ResponseEntity<Response<WalletItemDTO>> create(@Valid @RequestBody WalletItemDTO dto, BindingResult result){

        Response<WalletItemDTO> response = new Response<WalletItemDTO>();

        if(result.hasErrors()){
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.badRequest().body(response);
        }

        WalletItem wi =  service.save(WalletItemDTO.fromDTO(dto));

        response.setData(WalletItemDTO.toDTO(wi));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping(value = "/{wallet}")
    public ResponseEntity<Response<Page<WalletItemDTO>>> findBetweenDates(@PathVariable("wallet") Long wallet,
                                                                       @RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
                                                                       @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate,
                                                                       @RequestParam(name = "page", defaultValue = "0") int page){
        Response<Page<WalletItemDTO>> response = new Response<Page<WalletItemDTO>>();
        Page<WalletItem> items = service.findBetweenDates(wallet,startDate,endDate,page);
        Page<WalletItemDTO> dto = items.map(i -> WalletItemDTO.toDTO(i));
        response.setData(dto);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/type/{wallet}")
    public ResponseEntity<Response<List<WalletItemDTO>>> findByWalletIdAndType(@PathVariable("wallet") Long wallet,
                                                                          @RequestParam(name = "type") String type){

        Response<List<WalletItemDTO>> response = new Response<List<WalletItemDTO>>();
        List<WalletItem> list = service.findByWalletAndType(wallet, TypeEnum.getEnum(type));

        List<WalletItemDTO> dto = new ArrayList<>();
        list.forEach(i -> dto.add(WalletItemDTO.toDTO(i)));

        response.setData(dto);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/total/{wallet}")
    public ResponseEntity<Response<BigDecimal>> sumByWalletId(@PathVariable("wallet") Long wallet){

        Response<BigDecimal> response = new Response<BigDecimal>();
        BigDecimal value = service.sumByWalletId(wallet);

        response.setData(value == null ? BigDecimal.ZERO : value);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<Response<WalletItemDTO>> update(@Valid @RequestBody WalletItemDTO dto, BindingResult result){

        Response<WalletItemDTO> response = new Response<WalletItemDTO>();

        Optional<WalletItem> wi = service.findById(dto.getId());

        if(!wi.isPresent()){
            result.addError(new ObjectError("WalletItem","WalletItem não encontrado."));
        } else {
            if(wi.get().getWallet().getId().compareTo(dto.getWallet()) != 0){
                result.addError(new ObjectError("WalletItemChanged","Você não pode alterar a carteira."));
            }
        }

        if(result.hasErrors()){
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.badRequest().body(response);
        }

        WalletItem saved =  service.save(WalletItemDTO.fromDTO(dto));

        response.setData(WalletItemDTO.toDTO(saved));

        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping(value = "/{wallet}")
    public ResponseEntity<Response<String>> delete(@PathVariable("wallet") Long wallet){
        Response<String> response =  new Response<String>();

        Optional<WalletItem> wi = service.findById(wallet);

        if(!wi.isPresent()){
            response.getErrors().add("Carteira de id " + wallet + " não encontrada.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        service.deleteById(wallet);

        response.setData("Carteira de id " + wallet + " apagada com sucesso.");
        return ResponseEntity.ok().body(response);
    }
}
