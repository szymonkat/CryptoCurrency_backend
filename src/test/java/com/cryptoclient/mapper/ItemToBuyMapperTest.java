package com.cryptoclient.mapper;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.ExchangePortal;
import com.cryptoclient.domain.ItemToBuy;
import com.cryptoclient.dto.ItemToBuyDto;
import com.cryptoclient.service.interfaces.ExchangePortalService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ItemToBuyMapperTest {

    @InjectMocks
    private ItemToBuyMapper itemToBuyMapper;

    @Mock
    private ExchangePortalService exchangePortalService;

    @Test
    public void shouldMapToItemToBuy() {
        //Given
        ItemToBuyDto itemToBuyDto = new ItemToBuyDto(1L, 2L, 200.0);
        ExchangePortal exchangePortal = ExchangePortal.builder()
                .id(2L)
                .provider("coinLayer")
                .currencyToBuy(Currency.BTC)
                .currencyToPay(Currency.USD)
                .ratio(350.0)
                .time(LocalDateTime.of(2021, 12, 1, 7, 0))
                .build();

        when(exchangePortalService.findExchangePortalById(2L)).thenReturn(exchangePortal);
        //When
        ItemToBuy itemToBuy = itemToBuyMapper.mapToItemToBuy(itemToBuyDto);
        //Then
        Assert.assertEquals(itemToBuy.getId(), Long.valueOf(1));
        Assert.assertEquals(itemToBuy.getExchangePortal().getId(), Long.valueOf(2));
        Assert.assertEquals(itemToBuy.getQuantityToBuy(), 200.0, 0.001);
    }

    @Test
    public void shouldMapToItemToBuyDto() {
        //Given
        ExchangePortal exchangePortal = ExchangePortal.builder()
                .id(2L)
                .provider("coinLayer")
                .currencyToBuy(Currency.BTC)
                .currencyToPay(Currency.USD)
                .ratio(350.0)
                .time(LocalDateTime.of(2021, 12, 1, 7, 0))
                .build();

        ItemToBuy itemToBuy = new ItemToBuy(1L, exchangePortal, 200.0);
        //When
        ItemToBuyDto itemToBuyDto = itemToBuyMapper.mapToItemToBuyDto(itemToBuy);
        //Then
        Assert.assertEquals(itemToBuyDto.getId(), Long.valueOf(1));
        Assert.assertEquals(itemToBuyDto.getExchangePortalId(), Long.valueOf(2));
        Assert.assertEquals(itemToBuyDto.getQuantityToBuy(), 200.0, 0.001);
    }

    @Test
    public void shouldMapToItemToBuyList() {
        //Given
        ItemToBuyDto itemToBuyDto1 = new ItemToBuyDto(1L, 2L, 200.0);
        ItemToBuyDto itemToBuyDto2 = new ItemToBuyDto(3L, 4L, 500.0);
        ExchangePortal exchangePortal1 = ExchangePortal.builder()
                .id(2L)
                .provider("coinLayer")
                .currencyToBuy(Currency.BTC)
                .currencyToPay(Currency.USD)
                .ratio(350.0)
                .time(LocalDateTime.of(2021, 12, 1, 7, 0))
                .build();

        ExchangePortal exchangePortal2 = ExchangePortal.builder()
                .id(4L)
                .provider("coinLayer")
                .currencyToBuy(Currency.BTC)
                .currencyToPay(Currency.USD)
                .ratio(350.0)
                .time(LocalDateTime.of(2021, 12, 1, 7, 0))
                .build();

        when(exchangePortalService.findExchangePortalById(2L)).thenReturn(exchangePortal1);
        when(exchangePortalService.findExchangePortalById(4L)).thenReturn(exchangePortal2);

        List<ItemToBuyDto> itemToBuyDtoList = new ArrayList<>();
        itemToBuyDtoList.add(itemToBuyDto1);
        itemToBuyDtoList.add(itemToBuyDto2);
        //When
        List<ItemToBuy> itemToBuyList = itemToBuyMapper.mapToItemToBuyList(itemToBuyDtoList);
        //Then
        Assert.assertEquals(itemToBuyList.get(0).getId(), Long.valueOf(1));
        Assert.assertEquals(itemToBuyList.get(0).getExchangePortal().getId(), Long.valueOf(2));
        Assert.assertEquals(itemToBuyList.get(0).getQuantityToBuy(), 200.0, 0.001);
        Assert.assertEquals(itemToBuyList.get(1).getId(), Long.valueOf(3));
        Assert.assertEquals(itemToBuyList.get(1).getExchangePortal().getId(), Long.valueOf(4));
        Assert.assertEquals(itemToBuyList.get(1).getQuantityToBuy(), 500.0, 0.001);
    }

    @Test
    public void shouldMapToItemToBuyDtoList() {
        //Given
        ExchangePortal exchangePortal1 = ExchangePortal.builder()
                .id(2L)
                .provider("coinLayer")
                .currencyToBuy(Currency.BTC)
                .currencyToPay(Currency.USD)
                .ratio(350.0)
                .time(LocalDateTime.of(2021, 12, 1, 7, 0))
                .build();

        ExchangePortal exchangePortal2 = ExchangePortal.builder()
                .id(4L)
                .provider("coinLayer")
                .currencyToBuy(Currency.BTC)
                .currencyToPay(Currency.USD)
                .ratio(350.0)
                .time(LocalDateTime.of(2021, 12, 1, 7, 0))
                .build();

        ItemToBuy itemToBuy1 = new ItemToBuy(1L, exchangePortal1, 200.0);
        ItemToBuy itemToBuy2 = new ItemToBuy(3L, exchangePortal2, 500.0);

        List<ItemToBuy> itemToBuyList = new ArrayList<>();
        itemToBuyList.add(itemToBuy1);
        itemToBuyList.add(itemToBuy2);
        //When
        List<ItemToBuyDto> itemToBuyDtoList = itemToBuyMapper.mapToItemToBuyDtoList(itemToBuyList);
        //Then
        Assert.assertEquals(itemToBuyDtoList.get(0).getId(), Long.valueOf(1));
        Assert.assertEquals(itemToBuyDtoList.get(0).getExchangePortalId(), Long.valueOf(2));
        Assert.assertEquals(itemToBuyDtoList.get(0).getQuantityToBuy(), 200.0, 0.001);
        Assert.assertEquals(itemToBuyDtoList.get(1).getId(), Long.valueOf(3));
        Assert.assertEquals(itemToBuyDtoList.get(1).getExchangePortalId(), Long.valueOf(4));
        Assert.assertEquals(itemToBuyDtoList.get(1).getQuantityToBuy(), 500.0, 0.001);
    }

}
