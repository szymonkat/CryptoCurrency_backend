package com.cryptoclient.mapper;

import com.cryptoclient.domain.Wallet;
import com.cryptoclient.domain.WalletItem;
import com.cryptoclient.dto.WalletDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class WalletMapperTestSuite {

    @InjectMocks
    private WalletMapper walletMapper;

    @Test
    public void shouldMapToWallet() {
        //Given
        WalletDto walletDto = new WalletDto("wallet");
        //When
        Wallet wallet = walletMapper.mapToWallet(walletDto);
        //Then
        Assert.assertEquals(wallet.getName(), "wallet");
    }

    @Test
    public void shouldMapToWalletDto() {
        //Given
        Wallet wallet = new Wallet(2L,"wallet", new ArrayList<>());

        //When
        WalletDto walletDto = walletMapper.mapToWalletDto(wallet);
        //Then
        Assert.assertEquals(walletDto.getName(), "wallet");
    }

    @Test
    public void shouldMapToWalletList() {

    }

    @Test
    public void shouldMapToWalletDtoList() {

    }

}
