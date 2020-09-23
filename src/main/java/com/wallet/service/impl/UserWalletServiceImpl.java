package com.wallet.service.impl;

import com.wallet.entity.UserWallet;
import com.wallet.repository.UserWalletRepository;
import com.wallet.service.UserWalletService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserWalletServiceImpl implements UserWalletService {

    @Autowired
    UserWalletRepository repository;

    public UserWallet save(UserWallet uw){

        return repository.save(uw);

    }
}
