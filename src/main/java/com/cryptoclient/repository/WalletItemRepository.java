package com.cryptoclient.repository;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.Wallet;
import com.cryptoclient.domain.WalletItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletItemRepository extends JpaRepository<WalletItem, Long> {

    @Override
    <S extends WalletItem> S save(S WalletItem);


    Boolean existsByCurrencyAndWallet(Currency currency, Wallet wallet);

    WalletItem findByCurrencyAndWallet(Currency currency, Wallet wallet);

}
