package com.cryptoclient.service;

import com.cryptoclient.domain.Wallet;
import com.cryptoclient.repository.WalletRepository;
import com.cryptoclient.service.interfaces.WalletService;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.constraints.AssertTrue;
import java.util.*;

import static org.assertj.core.api.Assertions.not;
import static org.junit.Assert.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class WalletTestSuite {

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletRepository walletRepository;

    @Test
    public void shouldFindWalletById() {
        //Given
        Wallet wallet = new Wallet("Test");
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
        Wallet wallet1 = new Wallet("TestWallet1");
        Wallet wallet2 = new Wallet("TestWallet2");
        Wallet wallet3 = new Wallet("TestWallet3");
        List<Wallet> walletList = new ArrayList<>();
        //When
        walletService.createWallet(wallet1);
        walletService.createWallet(wallet2);
        walletService.createWallet(wallet3);

        walletList = walletService.getWallets();

        //Then
        assertEquals(walletList.size(), not(IsEmptyCollection.empty()));

        //Cleanup
        walletRepository.deleteById(wallet1.getId());
        walletRepository.deleteById(wallet2.getId());
        walletRepository.deleteById(wallet3.getId());
    }


    @Test
    public void shouldCreateAllWallet() {
        //Given

        //When

        //Then

        //Cleanup

    }

    @Test
    public void shouldUpdateWalletsName() {
        //Given

        //When

        //Then

        //Cleanup

    }


    @Test
    public void shouldDeleteWallet() {
        //Given

        //When

        //Then

        //Cleanup

    }


    @Test
    public void shouldSaveWallet() {
        //Given

        //When

        //Then

        //Cleanup

    }


    @Test
    public void shouldCheckIfWalletExistsById() {
        //Given

        //When

        //Then

        //Cleanup
    }


    @Test
    public void shouldCheckHowManyUsdWalletHas() {
        //Given

        //When

        //Then

        //Cleanup

    }

}
