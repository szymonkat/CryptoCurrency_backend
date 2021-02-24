package com.cryptoclient.service.interfaces;

import com.cryptoclient.domain.Wallet;
import com.cryptoclient.exceptions.NotFoundException;

import java.util.List;

public interface WalletService {
    Wallet createWallet(final Wallet wallet);

    Wallet updateWalletName(final Wallet wallet) throws NotFoundException;

    Wallet findWalletById(final Long walletId);

    boolean checkIfExistsById(final Long walletId);

    List<Wallet> getWallets();

    void deleteWallet(final Long id);

    Double checkHowManyUsdWalletHas(final Long walletId);

    Wallet save(final Wallet wallet);
}
