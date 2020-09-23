package com.wallet.service;

import com.wallet.entity.UserWallet;
import com.wallet.repository.UserWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserWalletService {

    UserWallet save(UserWallet uw);
}
