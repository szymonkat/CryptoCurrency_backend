package com.cryptoclient.mapper;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.Wallet;
import com.cryptoclient.domain.WalletItem;
import com.cryptoclient.dto.WalletItemDto;
import com.cryptoclient.service.interfaces.WalletService;
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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletItemMapperTest {

    @InjectMocks
    private WalletItemMapper walletItemMapper;

    @Mock
    private WalletService walletService;

    @Test
    public void shouldMapToWalletItem() {
        //Given
        WalletItemDto walletItemDto1 = new WalletItemDto(13L, 5L, Currency.BTC, 300.0);

        Wallet wallet5 = new Wallet(5L, "wallet5", null);

        when(walletService.findWalletById(5L)).thenReturn(wallet5);

        //When
        WalletItem walletItem = walletItemMapper.mapToWalletItem(walletItemDto1);

        //Then
        Assert.assertEquals(walletItem.getId(), Long.valueOf(13));
        Assert.assertEquals(walletItem.getWallet().getId(), Long.valueOf(5));
        Assert.assertEquals(walletItem.getCurrency(), Currency.BTC);
        Assert.assertEquals(walletItem.getQuantity(), 300.0, 0.001);
    }

    @Test
    public void shouldMapToWalletItemDto() {
        //Given
        Wallet wallet5 = new Wallet(5L, "wallet5", null);
        WalletItem walletItem1 = new WalletItem(13L, wallet5, Currency.BTC, 300.0);

        //When
        WalletItemDto walletItemDto = walletItemMapper.mapToWalletItemDto(walletItem1);

        //Then
        Assert.assertEquals(walletItemDto.getId(), Long.valueOf(13));
        Assert.assertEquals(walletItemDto.getWalletId(), Long.valueOf(5));
        Assert.assertEquals(walletItemDto.getCurrency(), Currency.BTC);
        Assert.assertEquals(walletItemDto.getQuantity(), 300.0, 0.001);
    }

    @Test
    public void shouldMapToWalletItemList() {
        //Given
        WalletItemDto walletItemDto1 = new WalletItemDto(13L, 5L, Currency.BTC, 300.0);
        WalletItemDto walletItemDto2 = new WalletItemDto(14L, 6L, Currency.USD, 300.0);

        List<WalletItemDto> walletItemDtoList = new ArrayList<>();
        walletItemDtoList.add(walletItemDto1);
        walletItemDtoList.add(walletItemDto2);

        Wallet wallet5 = new Wallet(5L, "wallet5", null);
        Wallet wallet6 = new Wallet(6L, "wallet6", null);

        when(walletService.findWalletById(5L)).thenReturn(wallet5);
        when(walletService.findWalletById(6L)).thenReturn(wallet6);

        //When
        List<WalletItem> walletItemList = walletItemMapper.mapToWalletItemList(walletItemDtoList);

        //Then
        Assert.assertEquals(walletItemList.get(0).getId(), Long.valueOf(13));
        Assert.assertEquals(walletItemList.get(0).getWallet().getId(), Long.valueOf(5));
        Assert.assertEquals(walletItemList.get(0).getCurrency(), Currency.BTC);
        Assert.assertEquals(walletItemList.get(0).getQuantity(), 300.0, 0.001);
        Assert.assertEquals(walletItemList.get(1).getId(), Long.valueOf(14));
        Assert.assertEquals(walletItemList.get(1).getWallet().getId(), Long.valueOf(6));
        Assert.assertEquals(walletItemList.get(1).getCurrency(), Currency.USD);
        Assert.assertEquals(walletItemList.get(1).getQuantity(), 300.0, 0.001);

    }

    @Test
    public void shouldMapToWalletItemDtoList() {
        //Given
        Wallet wallet5 = new Wallet(5L, "wallet5", null);
        Wallet wallet6 = new Wallet(6L, "wallet6", null);

        WalletItem walletItem1 = new WalletItem(13L, wallet5, Currency.BTC, 300.0);
        WalletItem walletItem2 = new WalletItem(14L, wallet6, Currency.USD, 300.0);

        List<WalletItem> walletItemList = new ArrayList<>();
        walletItemList.add(walletItem1);
        walletItemList.add(walletItem2);


        //When
        List<WalletItemDto> walletItemDtoList = walletItemMapper.mapToWalletItemDtoList(walletItemList);

        //Then
        Assert.assertEquals(walletItemDtoList.get(0).getId(), Long.valueOf(13));
        Assert.assertEquals(walletItemDtoList.get(0).getWalletId(), Long.valueOf(5));
        Assert.assertEquals(walletItemDtoList.get(0).getCurrency(), Currency.BTC);
        Assert.assertEquals(walletItemDtoList.get(0).getQuantity(), 300.0, 0.001);
        Assert.assertEquals(walletItemDtoList.get(1).getId(), Long.valueOf(14));
        Assert.assertEquals(walletItemDtoList.get(1).getWalletId(), Long.valueOf(6));
        Assert.assertEquals(walletItemDtoList.get(1).getCurrency(), Currency.USD);
        Assert.assertEquals(walletItemDtoList.get(1).getQuantity(), 300.0, 0.001);
    }
}
