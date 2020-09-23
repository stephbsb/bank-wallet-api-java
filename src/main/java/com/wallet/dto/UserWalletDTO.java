package com.wallet.dto;

import com.wallet.entity.User;
import com.wallet.entity.UserWallet;
import com.wallet.entity.Wallet;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserWalletDTO {

    private Long id;

    @NotNull(message = "Informe o id do usuario")
    private Long users; // users id

    @NotNull(message = "Informe o id da carteira")
    private Long wallet; // wallet id

    public static UserWallet fromDTO(UserWalletDTO dto) {
        UserWallet uw = new UserWallet();
        User u = new User();
        u.setId(dto.getUsers());

        Wallet w = new Wallet();
        w.setId(dto.getWallet());

        uw.setId(dto.getId());
        uw.setUsers(u);
        uw.setWallet(w);

        return uw;
    }

    public static UserWalletDTO toDTO(UserWallet uw) {
        UserWalletDTO dto = new UserWalletDTO();
        dto.setId(uw.getUsers().getId());
        dto.setUsers(uw.getWallet().getId());

        return dto;
    }

}
