package com.cryptoclient.service;

import com.cryptoclient.domain.Wallet;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletTest {

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletItemService walletItemService;

    @Autowired
    private WalletItemRepository walletItemRepository;

    @Test
    public void shouldFindWalletById() {
        //Given
        Wallet wallet = new Wallet("test_wallet_1");
        //When
        Wallet createdWallet = walletService.createWallet(wallet);
        Long createdWalletId = createdWallet.getId();
        Wallet walletFound = walletService.findWalletById(createdWalletId);
        //Then
        assertEquals(createdWallet.getId(), walletFound.getId());
        assertEquals(createdWallet.getName(), walletFound.getName());
        //Cleanup
        walletRepository.deleteById(createdWallet.getId());
    }

    @Test
    public void shouldGetWallets() {
        //Given
        Wallet wallet1 = new Wallet("test_wallet_2");
        Wallet wallet2 = new Wallet("test_wallet_3");
        Wallet wallet3 = new Wallet("test_wallet_4");
        List<Wallet> walletList = new ArrayList<>();
        //When
        walletService.createWallet(wallet1);
        walletService.createWallet(wallet2);
        walletService.createWallet(wallet3);

        walletList = walletService.getWallets();

        //Then
        assertTrue(!walletList.isEmpty());

        //Cleanup
        walletRepository.deleteById(wallet1.getId());
        walletRepository.deleteById(wallet2.getId());
        walletRepository.deleteById(wallet3.getId());
    }


    @Test
    public void shouldCreateWallet() {
        //Given
        Wallet wallet = new Wallet("test_wallet_5");
        //When
        Wallet newWallet = walletService.createWallet(wallet);
        //Then
        assertEquals("test_wallet_5", newWallet.getName());
        //Cleanup
        walletRepository.deleteById(wallet.getId());
    }

    @Test
    public void shouldUpdateWalletsName() {
        //Given
        Wallet wallet = new Wallet("test_wallet_6");
        walletService.createWallet(wallet);
        Long walletId = wallet.getId();
        Wallet updatedWallet = new Wallet("test_wallet_7");
        updatedWallet.setId(walletId);
        //When
        Wallet newWallet = walletService.updateWalletName(updatedWallet);
        //Then
        assertEquals(newWallet.getId(), updatedWallet.getId());
        assertEquals(newWallet.getName(), "test_wallet_7");
        //Cleanup
        walletRepository.deleteById(newWallet.getId());
    }

    @Test
    public void shouldDeleteWallet() {
        //Given
        Wallet wallet = new Wallet("test_wallet_8");
        walletService.createWallet(wallet);
        Long walletId = wallet.getId();
        List<Wallet> walletList = new ArrayList<>();
        walletList = walletService.getWallets();
        int walletListSize = walletList.size();
        //When
        walletService.deleteWallet(walletId);
        //Then
        walletList = walletService.getWallets();
        int walletListAfterDelete = walletList.size();
        assertEquals(walletListSize, walletListAfterDelete + 1);
        //Cleanup
    }

    @Test
    public void shouldSaveWallet() {
        //Given
        Wallet wallet = new Wallet("test_wallet_9");
        //When
        walletService.save(wallet);
        Long walletId = wallet.getId();
        //Then
        assertEquals(walletService.findWalletById(walletId).getName(),"test_wallet_9");
        //Cleanup
        walletRepository.deleteById(wallet.getId());
    }

    @Test
    public void shouldCheckIfWalletExistsById() {
        //Given
        Wallet wallet = new Wallet("test_wallet_10");
        walletService.save(wallet);
        //When
        boolean walletExists = walletService.checkIfExistsById(wallet.getId());
        //Then
        assertEquals(walletExists, true);
        //Cleanup
        walletRepository.deleteById(wallet.getId());
    }
}
