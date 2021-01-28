package com.cryptoclient.service;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.Wallet;
import com.cryptoclient.domain.WalletItem;
import com.cryptoclient.exceptions.NotFoundException;
import com.cryptoclient.repository.WalletItemRepository;
import com.cryptoclient.service.interfaces.WalletItemService;
import com.cryptoclient.service.interfaces.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WalletItemTestSuite {

    @Autowired
    private WalletItemService walletItemService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletItemRepository walletItemRepository;

    LocalDateTime now = LocalDateTime.now();
    String various = now.toString();
    Wallet wallet = new Wallet(various + "1");


/*    @Test
    public void shouldModifyWallet() {
        //Given
        walletService.createWallet(wallet);
        WalletItem walletItem = new WalletItem(wallet, Currency.USD, 300.0);
        walletItemService.save(walletItem);
        Long walletItemLong = walletItem.getId();
        WalletItem newWalletItem = new WalletItem(wallet, Currency.BTC, 200.0);
        newWalletItem.setId(walletItemLong);
        //When
        walletItemService.modifyWalletItem(newWalletItem);
        WalletItem updatedWalletItem = walletItemService.findWalletItemById(walletItemLong);
        //Then
        assertEquals(updatedWalletItem.getCurrency(), newWalletItem.getCurrency());
        assertEquals(updatedWalletItem.getQuantity(), newWalletItem.getQuantity());
        //CleanUp
        //walletItemRepository.deleteById(walletItemLong);

    }*/

    @Test
    public void shouldUpdateWalletItem() {
    }

    @Test
    public void shouldFindWalletItemById() {
    }

    @Test
    public void shouldGetWalletItems() {
    }

    @Test
    public void shouldDeleteWalletItem() {
    }

    @Test
    public void shouldSaveWalletItem() {
    }

    @Test
    public void shouldReturnUsdWalletItem() {
    }

    @Test
    public void shouldReturnCurrencyWalletItem() {
    }

}
