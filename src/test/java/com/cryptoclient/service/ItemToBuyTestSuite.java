package com.cryptoclient.service;

import com.cryptoclient.domain.*;
import com.cryptoclient.repository.ExchangePortalRepository;
import com.cryptoclient.repository.ItemToBuyRepository;
import com.cryptoclient.repository.WalletItemRepository;
import com.cryptoclient.repository.WalletRepository;
import com.cryptoclient.service.interfaces.ExchangePortalService;
import com.cryptoclient.service.interfaces.ItemToBuyService;
import com.cryptoclient.service.interfaces.WalletItemService;
import com.cryptoclient.service.interfaces.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemToBuyTestSuite {

    @Autowired
    private ItemToBuyService itemToBuyService;

    @Autowired
    private ExchangePortalService exchangePortalService;

    @Autowired
    private ItemToBuyRepository itemToBuyRepository;

    @Autowired
    private ExchangePortalRepository exchangePortalRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletItemService walletItemService;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletItemRepository walletItemRepository;


    ExchangePortal exchangePortal = new ExchangePortal("nomics", Currency.XMR, Currency.USD,
            10.0,  LocalDateTime.of(2021, 12, 2, 6, 23));

    ItemToBuy itemToBuy = new ItemToBuy(exchangePortal, 200.0);

    @Test
    public void shouldCreateItemToBuy() {
        //Given
        exchangePortalService.save(exchangePortal);
        Long exchangePortalLong = exchangePortal.getId();
        itemToBuyService.createItemToBuy(itemToBuy);
        Long itemToBuyLong = itemToBuy.getId();
        //When
        //Then
        assertEquals(itemToBuy.getExchangePortal().getRatio(), 10.0, 0.0001);
        assertEquals(itemToBuy.getQuantityToBuy(), 200.0, 0.0001);
        //Clean-up
        itemToBuyRepository.deleteById(itemToBuyLong);
        exchangePortalRepository.deleteById(exchangePortalLong);
    }

    @Test
    public void shouldFindItemToBuy() {
        //Given
        exchangePortalService.save(exchangePortal);
        Long exchangePortalLong = exchangePortal.getId();
        itemToBuyService.createItemToBuy(itemToBuy);
        Long itemToBuyLong = itemToBuy.getId();
        //When
        ItemToBuy itemToBuyById = itemToBuyService.findItemToBuyById(itemToBuyLong);
        //Then
        assertEquals(itemToBuyById.getId(), itemToBuyLong);
        //Clean-up
        itemToBuyRepository.deleteById(itemToBuyLong);
        exchangePortalRepository.deleteById(exchangePortalLong);
    }

    @Test
    public void shouldGetItemsToBuy() {
        //Given
        exchangePortalService.save(exchangePortal);
        Long exchangePortalLong = exchangePortal.getId();
        itemToBuyService.createItemToBuy(itemToBuy);
        Long itemToBuyLong = itemToBuy.getId();
        List<ItemToBuy> itemToBuyList = new ArrayList<>();
        //When
        itemToBuyList = itemToBuyService.getItemToBuys();
        //Then
        assertTrue(!itemToBuyList.isEmpty());
        //Clean-up
        itemToBuyRepository.deleteById(itemToBuyLong);
        exchangePortalRepository.deleteById(exchangePortalLong);
    }

    @Test
    public void shouldSaveItemToBuy() {
        //Given
        exchangePortalService.save(exchangePortal);
        Long exchangePortalLong = exchangePortal.getId();
        //When
        itemToBuyService.save(itemToBuy);
        Long itemToBuyLong = itemToBuy.getId();
        //Then
        assertEquals(itemToBuy.getExchangePortal().getRatio(), 10.0, 0.0001);
        assertEquals(itemToBuy.getQuantityToBuy(), 200.0, 0.0001);
        //Clean-up
        itemToBuyRepository.deleteById(itemToBuyLong);
        exchangePortalRepository.deleteById(exchangePortalLong);
    }

    @Test
    public void shouldFinalizeItemToBuy() {
        //Given
        exchangePortalService.save(exchangePortal);
        Long exchangePortalLong = exchangePortal.getId();

        itemToBuyService.createItemToBuy(itemToBuy);
        Long itemToBuyLong = itemToBuy.getId();

        Wallet wallet = new Wallet(LocalDateTime.now() + "test");
        walletService.save(wallet);
        Long walletLong = wallet.getId();

        WalletItem walletItem = new WalletItem(wallet, Currency.USD, 1000000.0);
        walletItemService.postWalletItem(walletItem);
        Long walletItemLong = walletItem.getId();

        wallet.addWalletItem(walletItem);

        //When
        itemToBuyService.finalizeItemToBuy(itemToBuyLong, walletLong);

        //Then
        List<WalletItem> itemsList = walletItemService.getWalletItems().stream()
                .filter(n -> n.getWallet().getId() == walletLong)
                .collect(Collectors.toList());

        Optional<WalletItem> walletItemUsd = itemsList.stream()
                .findAny()
                .filter(n -> n.getCurrency() == Currency.USD);

        WalletItem walletItemXmr = walletItemService.returnCurrencyWalletItem(walletLong, Currency.XMR);

        System.out.println("TEST");
        System.out.println(itemsList);
        System.out.println(walletItemUsd);
        System.out.println(walletItemXmr);
        System.out.println("TEST");
//        assertEquals(998000.0, walletItemUsd.get().getQuantity(), 0.001);
//        assertEquals(200.0, walletItemXmr.get().getQuantity(), 0.001);

        //Clean-up
        exchangePortalRepository.deleteById(exchangePortalLong);
        walletItemRepository.deleteById(walletItemLong);
        walletRepository.deleteById(walletLong);
    }

    @Test
    public void shouldDeleteItemToBuy() {
        /*//Given
        exchangePortalService.save(exchangePortal);
        Long exchangePortalLong = exchangePortal.getId();
        //When
        itemToBuyService.save(itemToBuy);
        Long itemToBuyLong = itemToBuy.getId();
        //Then
        assertEquals(itemToBuy.getExchangePortal().getRatio(), 10.0, 0.0001);
        assertEquals(itemToBuy.getQuantityToBuy(), 200.0, 0.0001);
        //Clean-up
        itemToBuyRepository.deleteById(itemToBuyLong);
        exchangePortalRepository.deleteById(exchangePortalLong);*/
    }
}