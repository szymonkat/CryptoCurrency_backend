package com.cryptoclient.service;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.Wallet;
import com.cryptoclient.domain.WalletItem;
import com.cryptoclient.repository.WalletItemRepository;
import com.cryptoclient.repository.WalletRepository;
import com.cryptoclient.service.interfaces.WalletItemService;
import com.cryptoclient.service.interfaces.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletItemTest {

    LocalDateTime now = LocalDateTime.now();
    String various = now.toString();
    Wallet wallet = new Wallet(various + "1");
    @Autowired
    private WalletItemService walletItemService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private WalletItemRepository walletItemRepository;
    @Autowired
    private WalletRepository walletRepository;

    @Test
    public void shouldPostWalletItem() {
        //Given
        walletService.createWallet(wallet);
        WalletItem walletItem = new WalletItem(wallet, Currency.USD, 300.0);

        //When
        walletItemService.postWalletItem(walletItem);
        //Then
        assertEquals(300.0, walletItem.getQuantity(), 0.001);
        assertEquals(Currency.USD, walletItem.getCurrency());
        assertEquals(various + "1", walletItem.getWallet().getName());

        //CleanUp
        walletItemRepository.deleteById(walletItem.getId());
        walletRepository.deleteById(wallet.getId());
    }

    @Test
    public void shouldUpdateWalletItem() {
        //Given
        walletService.createWallet(wallet);
        WalletItem walletItem = new WalletItem(wallet, Currency.USD, 300.0);
        walletItemService.save(walletItem);
        Long walletItemLong = walletItem.getId();

        WalletItem newWalletItem = new WalletItem(wallet, Currency.BTC, 200.0);
        newWalletItem.setId(walletItemLong);
        //When
        walletItemService.updateWalletItem(newWalletItem);

        //Then
        assertEquals(walletItem.getId(), newWalletItem.getId());
        assertEquals(Currency.BTC, newWalletItem.getCurrency());
        assertEquals(200.0, newWalletItem.getQuantity(), 0.001);
        //CleanUp
        walletItemRepository.deleteById(walletItemLong);
        walletRepository.deleteById(wallet.getId());
    }

    @Test
    public void shouldFindWalletItemById() {
        //Given
        walletService.createWallet(wallet);
        WalletItem walletItem = new WalletItem(wallet, Currency.USD, 300.0);
        walletItemService.postWalletItem(walletItem);

        //When
        WalletItem walletItemFoundById = walletItemService.findWalletItemById(walletItem.getId());

        //Then
        assertEquals(various + "1", walletItemFoundById.getWallet().getName());
        assertEquals(Currency.USD, walletItemFoundById.getCurrency());
        assertEquals(300.0, walletItemFoundById.getQuantity(), 0.001);

        //CleanUp
        walletItemRepository.deleteById(walletItem.getId());
        walletRepository.deleteById(wallet.getId());
    }

    @Test
    public void shouldGetWalletItems() {
        //Given
        walletService.createWallet(wallet);
        WalletItem walletItem = new WalletItem(wallet, Currency.USD, 300.0);
        walletItemService.postWalletItem(walletItem);
        List<WalletItem> walletItemList = new ArrayList<>();

        //When
        walletItemList = walletItemService.getWalletItems();

        //Then
        assertFalse(walletItemList.isEmpty());

        //CleanUp
        walletItemRepository.deleteById(walletItem.getId());
        walletRepository.deleteById(wallet.getId());
    }

    @Test
    public void shouldDeleteWalletItem() {
        //Given
        walletService.createWallet(wallet);
        WalletItem walletItem = new WalletItem(wallet, Currency.USD, 300.0);

        //When
        walletItemService.save(walletItem);
        //Then
        assertEquals(300.0, walletItem.getQuantity(), 0.001);
        assertEquals(Currency.USD, walletItem.getCurrency());
        assertEquals(various + "1", walletItem.getWallet().getName());

        //CleanUp
        walletItemRepository.deleteById(walletItem.getId());
        walletRepository.deleteById(wallet.getId());
    }

    @Test
    public void shouldSaveWalletItem() {
        //Given
        walletService.createWallet(wallet);
        WalletItem walletItem = new WalletItem(wallet, Currency.USD, 300.0);

        //When
        walletItemService.save(walletItem);
        //Then
        assertEquals(300.0, walletItem.getQuantity(), 0.001);
        assertEquals(Currency.USD, walletItem.getCurrency());
        assertEquals(various + "1", walletItem.getWallet().getName());

        //CleanUp
        walletItemRepository.deleteById(walletItem.getId());
        walletRepository.deleteById(wallet.getId());
    }

    @Test
    public void shouldReturnUsdWalletItem() {
        //Given
        walletService.createWallet(wallet);
        Long walletId = wallet.getId();
        WalletItem walletItem = new WalletItem(wallet, Currency.USD, 300.0);
        walletItemService.save(walletItem);

        //When
        WalletItem walletItemUsd = walletItemService.returnUsdWalletItem(walletId);

        //Then
        assertEquals(Currency.USD, walletItemUsd.getCurrency());
        assertEquals(300.0, walletItemUsd.getQuantity(), 0.001);

        //CleanUp
        walletItemRepository.deleteById(walletItem.getId());
        walletRepository.deleteById(wallet.getId());
    }

    @Test
    public void shouldReturnCurrencyWalletItem() {
        //Given
        walletService.createWallet(wallet);
        Long walletId = wallet.getId();
        WalletItem walletItem = new WalletItem(wallet, Currency.BTC, 200.0);
        walletItemService.save(walletItem);

        //When
        WalletItem walletItemBtc = walletItemService.returnCurrencyWalletItem(walletId, Currency.BTC);

        //Then
        assertEquals(Currency.BTC, walletItemBtc.getCurrency());
        assertEquals(200.0, walletItemBtc.getQuantity(), 0.001);

        //CleanUp
        walletItemRepository.deleteById(walletItem.getId());
        walletRepository.deleteById(wallet.getId());
    }

}
