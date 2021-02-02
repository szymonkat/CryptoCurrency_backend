package com.cryptoclient.service.implementations;

import com.cryptoclient.domain.ExchangePortal;
import com.cryptoclient.domain.ItemToBuy;
import com.cryptoclient.domain.WalletItem;
import com.cryptoclient.exceptions.ExchangePortalPriceTooOldException;
import com.cryptoclient.exceptions.NotEnoughUsdInTheWalletException;
import com.cryptoclient.exceptions.NotFoundException;
import com.cryptoclient.repository.ItemToBuyRepository;
import com.cryptoclient.service.interfaces.ExchangePortalService;
import com.cryptoclient.service.interfaces.ItemToBuyService;
import com.cryptoclient.service.interfaces.WalletItemService;
import com.cryptoclient.service.interfaces.WalletService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ItemToBuyServiceImpl implements ItemToBuyService {

    private final ItemToBuyRepository itemToBuyRepository;
    private final WalletService walletService;
    private final WalletItemService walletItemService;
    private final ExchangePortalService exchangePortalService;

    @Override
    public ItemToBuy createItemToBuy(ItemToBuy itemToBuy) {
        return itemToBuyRepository.save(itemToBuy);
    }

    @Override
    public ItemToBuy findItemToBuyById(Long itemToBuyId) {
        return itemToBuyRepository.findById(itemToBuyId).orElseThrow(
                () -> new NotFoundException("Item to buy with id " + itemToBuyId + " does not exist"));
    }

    @Override
    public List<ItemToBuy> getItemToBuys() {
        return itemToBuyRepository.findAll();
    }

    @Override
    public void deleteItemToBuy(Long id) {
        findItemToBuyById(id);
        itemToBuyRepository.deleteById(id);
    }

    @Override
    public ItemToBuy save(ItemToBuy itemToBuy) throws ExchangePortalPriceTooOldException {
        ExchangePortal exchangePortal = exchangePortalService.findExchangePortalById(itemToBuy.getExchangePortal().getId());
        if (exchangePortal.getItemToBuy() == null) {
            return itemToBuyRepository.save(itemToBuy);
        } else {
            throw new ExchangePortalPriceTooOldException("You cannot create Item to Buy with Exchange Portal already taken");
        }
    }

    @Override
    public void finalizeItemToBuy(Long itemToBuyId, Long walletId) throws NotFoundException, ExchangePortalPriceTooOldException,
            NotEnoughUsdInTheWalletException {
        ItemToBuy itemToBuy = findItemToBuyById(itemToBuyId);

        if (checkIfTimeIsNotOlderThen20Min(itemToBuy.getExchangePortal())
                && checkIfWalletHasSufficientFunds(walletId, itemToBuy)) {
            //1) Subtract USD amount
            WalletItem walletItem = walletItemService.returnUsdWalletItem(walletId);
            Double oldValue = walletItem.getQuantity();
            Double costValue = itemToBuy.getQuantityToBuy() * itemToBuy.getExchangePortal().getRatio();
            walletItem.setQuantity(oldValue - costValue);
            walletItemService.save(walletItem);
            //2) Add bought wallet item
            WalletItem addWalletItem = walletItemService.returnCurrencyWalletItem(walletId, itemToBuy.getExchangePortal().getCurrencyToBuy());
            Double oldCurrencyValue = addWalletItem.getQuantity();
            Double newCurrencyValue = oldCurrencyValue + itemToBuy.getQuantityToBuy();
            addWalletItem.setQuantity(newCurrencyValue);
            walletItemService.save(addWalletItem);
            //3) Delete itemToBuy
            deleteItemToBuy(itemToBuyId);
        } else { // Error handling
            if (!checkIfTimeIsNotOlderThen20Min(itemToBuy.getExchangePortal()))
                throw new ExchangePortalPriceTooOldException("Your exchange Portal price might be too old (20 min validation)");
            if (!checkIfWalletHasSufficientFunds(walletId, itemToBuy))
                throw new NotEnoughUsdInTheWalletException("You don't have sufficient funds in USD");
            else throw new NotFoundException("Common error");
        }
    }

    private boolean checkIfTimeIsNotOlderThen20Min(ExchangePortal exchangePortal) {
        LocalDateTime tenMinutesOld = LocalDateTime.now().minusMinutes(20);
        return exchangePortal.getTime().isAfter(tenMinutesOld);
    }

    private boolean checkIfWalletHasSufficientFunds(Long walletId, ItemToBuy itemToBuy) {
        Double walletUsdAmount = walletService.checkHowManyUsdWalletHas(walletId);
        return itemToBuy.getQuantityToBuy() * itemToBuy.getExchangePortal().getRatio() < walletUsdAmount;
    }

}