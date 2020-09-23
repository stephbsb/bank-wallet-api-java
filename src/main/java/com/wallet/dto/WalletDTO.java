package com.wallet.dto;

import com.wallet.entity.User;
import com.wallet.entity.Wallet;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class WalletDTO {

    private Long id;

    @NotNull(message = "Nome não pode ser nulo")
    @Length(min = 3, message = "Nome deve conter pelo menos 3 caracteres")
    private String name;

    @NotNull(message = "Valor inválido")
    private BigDecimal value;

    public static Wallet fromDTO(WalletDTO dto) {
        Wallet w = new Wallet();
        w.setId(dto.getId());
        w.setName(dto.getName());
        w.setValue(dto.getValue());

        return w;
    }

    public static WalletDTO toDTO(Wallet w) {
        WalletDTO dto = new WalletDTO();
        dto.setId(w.getId());
        dto.setName(w.getName());
        dto.setValue(w.getValue());

        return dto;
    }

}
