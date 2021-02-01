package com.cryptoclient.mapper;

import com.cryptoclient.config.ServiceConfig;
import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.Wallet;
import com.cryptoclient.domain.WalletItem;
import com.cryptoclient.dto.WalletDto;
import com.cryptoclient.dto.WalletItemDto;
import com.cryptoclient.service.implementations.WalletServiceImpl;
import com.cryptoclient.service.interfaces.WalletService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class WalletMapperTestSuite {

    @InjectMocks
    private WalletMapper walletMapper;

    @InjectMocks
    private WalletItemMapper walletItemMapper;

    @Mock
    private ServiceConfig serviceConfig;

    @Mock
    private WalletService walletService;

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

        WalletItemDto walletItemDto1 = new WalletItemDto(13L, 5L, Currency.BTC, 300.0);
        WalletItemDto walletItemDto2 = new WalletItemDto(14L, 6L, Currency.USD, 300.0);

        List<WalletItemDto> walletItemDtoList = new ArrayList<>();
        walletItemDtoList.add(walletItemDto1);
        walletItemDtoList.add(walletItemDto2);

        walletDto1.setWalletItemList(walletItemDtoList);
        walletDto2.setWalletItemList(walletItemDtoList);

        List<WalletDto> walletDtoList = new ArrayList<>();
        walletDtoList.add(walletDto1);
        walletDtoList.add(walletDto2);

        //When
        List<Wallet> walletList = walletMapper.mapToWalletList(walletDtoList);

        System.out.println(walletDto1.getWalletItemList().toString());
        System.out.println(walletList);


        //Then
        Assert.assertEquals(walletList.get(0).getId(), Long.valueOf(5));
        Assert.assertEquals(walletList.get(0).getName(), "wallet1");
        Assert.assertEquals(walletList.get(0).getWalletItemList().get(0).getId(), Long.valueOf(13));
        Assert.assertEquals(walletList.get(0).getWalletItemList().get(0).getWallet().getId(), Long.valueOf(5));
        Assert.assertEquals(walletList.get(0).getWalletItemList().get(0).getCurrency(), Currency.BTC);
        Assert.assertEquals(walletList.get(0).getWalletItemList().get(0).getQuantity(), 300.0, 0.001);
        Assert.assertEquals(walletList.get(0).getWalletItemList().get(1).getId(), Long.valueOf(14));
        Assert.assertEquals(walletList.get(0).getWalletItemList().get(1).getWallet().getId(), Long.valueOf(5));
        Assert.assertEquals(walletList.get(0).getWalletItemList().get(1).getCurrency(), Currency.USD);
        Assert.assertEquals(walletList.get(0).getWalletItemList().get(1).getQuantity(), 300.0, 0.001);
        Assert.assertEquals(walletList.get(1).getId(), Long.valueOf(6));
        Assert.assertEquals(walletList.get(1).getName(), "wallet2");
        Assert.assertEquals(walletList.get(1).getWalletItemList().get(0).getId(), Long.valueOf(13));
        Assert.assertEquals(walletList.get(1).getWalletItemList().get(0).getWallet().getId(), Long.valueOf(6));
        Assert.assertEquals(walletList.get(1).getWalletItemList().get(0).getCurrency(), Currency.BTC);
        Assert.assertEquals(walletList.get(1).getWalletItemList().get(0).getQuantity(), 300.0, 0.001);
        Assert.assertEquals(walletList.get(1).getWalletItemList().get(1).getId(), Long.valueOf(14));
        Assert.assertEquals(walletList.get(1).getWalletItemList().get(1).getWallet().getId(), Long.valueOf(6));
        Assert.assertEquals(walletList.get(1).getWalletItemList().get(1).getCurrency(), Currency.USD);
        Assert.assertEquals(walletList.get(1).getWalletItemList().get(1).getQuantity(), 300.0, 0.001);
    }

    @Test
    public void shouldMapToWalletDtoList() {

    }

    @Test
    public void testIfWalletItemMapperWorks() {
        WalletItemDto walletItemDto1 = new WalletItemDto(13L, 5L, Currency.BTC, 300.0);
        WalletItemDto walletItemDto2 = new WalletItemDto(14L, 6L, Currency.USD, 300.0);

        List<WalletItemDto> walletItemDtoList = new ArrayList<>();
        walletItemDtoList.add(walletItemDto1);
        walletItemDtoList.add(walletItemDto2);

        List<WalletItem> walletItemList = walletItemMapper.mapToWalletItemList(walletItemDtoList);

        System.out.println("TESTING");
        System.out.println(walletItemList);
        System.out.println("TESTING_ENDS");
    }

}
