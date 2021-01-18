package com.cryptoclient.service;

import com.cryptoclient.domain.Wallet;
import com.cryptoclient.repository.WalletRepository;
import com.cryptoclient.service.interfaces.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WalletTestSuite {

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletRepository walletRepository;

    private Wallet wallet = new Wallet("TestWallet");

    @Test
    public void shouldGetAllWallets() {
        //Given
        
        //When
        Wallet createdWallet = walletService.createWallet(wallet);
        //Then
        assertNotEquals(createdWallet.getId(), null);
        //Cleanup
        walletRepository.deleteById(createdWallet.getId());
    }
}

//Given
//When
//Then
//Cleanup