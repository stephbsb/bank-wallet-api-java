package com.wallet.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wallet.entity.Wallet;
import com.wallet.entity.WalletItem;
import com.wallet.util.enums.TypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class WalletItemDTO {

    private Long id;
    @NotNull(message = "Insira uma data")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private Date date;
    @NotNull(message = "Insira um tipo")
    @Pattern(regexp = "^(ENTRADA|SAÍDA)$",message = "Para o tipo são somento aceitos os valores ENTRADA ou SAÍDA")
    private String type;
    @NotNull(message = "Insira uma descrição")
    @Length(min = 5, message = "Deve ter no minimo 5 caracteres")
    private String description;
    @NotNull(message = "Insira um valor")
    private BigDecimal value;
    @NotNull(message = "Insira um valor")
    private Long wallet;

    public static WalletItem fromDTO(WalletItemDTO dto){
        WalletItem wi = new WalletItem();
        wi.setId(dto.getId());
        wi.setDescription(dto.getDescription());
        wi.setDate(dto.getDate());
        wi.setType(TypeEnum.getEnum(dto.getType()));
        wi.setValue(dto.getValue());

        Wallet w = new Wallet();
        w.setId(dto.getWallet());
        wi.setWallet(w);

        return wi;
    }

    public static WalletItemDTO toDTO(WalletItem wi){
        WalletItemDTO dto = new WalletItemDTO();
        dto.setId(wi.getId());
        dto.setDescription(wi.getDescription());
        dto.setDate(wi.getDate());
        dto.setType(wi.getType().getValue());
        dto.setValue(wi.getValue());
        dto.setWallet(wi.getWallet().getId());

        return dto;
    }
}
