package com.cryptoclient.service.interfaces;

import com.cryptoclient.domain.ItemToBuy;

import java.util.List;

public interface ItemToBuyService {
    List<ItemToBuy> getItemToBuys();

    ItemToBuy createItemToBuy(final ItemToBuy itemToBuy);

    ItemToBuy findItemToBuyById(final Long itemToBuyId);

    void deleteItemToBuy(Long id);

    ItemToBuy save(final ItemToBuy itemToBuy);

    void finalizeItemToBuy(final Long itemToBuyId, final Long walletId);
}
