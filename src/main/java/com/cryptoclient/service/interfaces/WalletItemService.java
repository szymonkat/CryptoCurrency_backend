package com.cryptoclient.service.interfaces;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.WalletItem;
import com.cryptoclient.exceptions.NotFoundException;

import java.util.List;


public interface WalletItemService {

    WalletItem postWalletItem(final WalletItem walletItem);

    WalletItem updateWalletItem(final WalletItem walletItem) throws NotFoundException;

    WalletItem findWalletItemById(final Long walletItemId);

    List<WalletItem> getWalletItems();

    void deleteWalletItem(final Long id);

    WalletItem save(final WalletItem walletItem);

    WalletItem returnUsdWalletItem(final Long walletId) throws NotFoundException;

    WalletItem returnCurrencyWalletItem(final Long walletId, final Currency currency) throws NotFoundException;
}
