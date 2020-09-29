package com.wallet.service.impl;

import com.wallet.entity.WalletItem;
import com.wallet.repository.WalletItemRepository;
import com.wallet.service.WalletItemService;
import com.wallet.util.enums.TypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WalletItemServiceImpl implements WalletItemService {

    @Autowired
    WalletItemRepository repository;

    private int itemsPerPage = 10;

    @Override
    public WalletItem save(WalletItem i) {
        return repository.save(i);
    }


    @Override
    public Optional<WalletItem> findById(Long wallet) {
        return repository.findById(wallet);

    }

    @Override
    public Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page) {
        System.out.println("pages: " + page + " " + itemsPerPage);
        return repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(wallet, start, end, PageRequest.of(page, itemsPerPage));

    }


    @Override
    public List<WalletItem> findByWalletAndType(Long wallet, TypeEnum type) {
        return repository.findByWalletIdAndType(wallet,type);
    }

    @Override
    public BigDecimal sumByWalletId(Long wallet) {
        return repository.sumByWalletId(wallet);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
