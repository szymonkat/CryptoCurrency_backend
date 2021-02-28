package com.cryptoclient.mapper;

import com.cryptoclient.domain.Wallet;
import com.cryptoclient.dto.WalletDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletMapperTest {

    @InjectMocks
    private WalletMapper walletMapper;

    @Mock
    private WalletItemMapper walletItemMapper;

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
        Wallet wallet = new Wallet(4L, "wallet", null);

        //When
        WalletDto walletDto = walletMapper.mapToWalletDto(wallet);

        //Then
        Assert.assertEquals(walletDto.getId(), Long.valueOf(4));
        Assert.assertEquals(walletDto.getName(), "wallet");
    }

    @Test
    public void shouldMapToWalletList() {
        WalletDto walletDto1 = new WalletDto(5L, "wallet1", null);
        WalletDto walletDto2 = new WalletDto(6L, "wallet2", null);

        List<WalletDto> walletDtoList = new ArrayList<>();
        walletDtoList.add(walletDto1);
        walletDtoList.add(walletDto2);

        //When
        List<Wallet> walletList = walletMapper.mapToWalletList(walletDtoList);

        //Then
        Assert.assertEquals(walletList.get(0).getId(), Long.valueOf(5));
        Assert.assertEquals(walletList.get(0).getName(), "wallet1");
        Assert.assertEquals(walletList.get(1).getId(), Long.valueOf(6));
        Assert.assertEquals(walletList.get(1).getName(), "wallet2");
    }

    @Test
    public void shouldMapToWalletDtoList() {
        Wallet wallet1 = new Wallet(5L, "wallet1", null);
        Wallet wallet2 = new Wallet(6L, "wallet2", null);

        List<Wallet> walletList = new ArrayList<>();
        walletList.add(wallet1);
        walletList.add(wallet2);

        //When
        List<WalletDto> walletDtoList = walletMapper.mapToWalletDtoList(walletList);

        //Then
        Assert.assertEquals(walletDtoList.get(0).getId(), Long.valueOf(5));
        Assert.assertEquals(walletDtoList.get(0).getName(), "wallet1");
        Assert.assertEquals(walletDtoList.get(1).getId(), Long.valueOf(6));
        Assert.assertEquals(walletDtoList.get(1).getName(), "wallet2");
    }
}
