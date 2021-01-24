package com.cryptoclient.service;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.WalletItem;
import com.cryptoclient.exceptions.NotFoundException;
import com.cryptoclient.repository.WalletItemRepository;
import com.cryptoclient.service.interfaces.WalletItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WalletItemTestSuite {

    @Autowired
    private WalletItemService walletItemService;

    @Autowired
    private WalletItemRepository walletItemRepository;

    LocalDateTime now = LocalDateTime.now();
    String various = now.toString();

    @Test
    public void shouldModifyWallet() {
        //Given
        //When
        //Then
        //CleanUp
    }

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
