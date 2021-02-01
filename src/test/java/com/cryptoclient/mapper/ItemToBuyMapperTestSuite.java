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

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ItemToBuyMapperTestSuite {

    @InjectMocks
    private ItemToBuyMapper itemToBuyMapper;

    @Mock
    private ExchangePortalService exchangePortalService;

    @Test
    public void shouldMapToItemToBuy() {
        //Given
        ItemToBuyDto itemToBuyDto = new ItemToBuyDto(1L, 2L, 200.0);
        ExchangePortal exchangePortal = new ExchangePortal(2L, "coinlayer", Currency.BTC, Currency.USD,
                350.0, LocalDateTime.of(2021, 12, 1, 7, 0));
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
        ExchangePortal exchangePortal = new ExchangePortal(2L, "coinlayer", Currency.BTC, Currency.USD,
                350.0, LocalDateTime.of(2021, 12, 1, 7, 0));
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
        ItemToBuyDto itemToBuyDto = new ItemToBuyDto(1L, 2L, 200.0);
        ItemToBuyDto itemToBuyDto = new ItemToBuyDto(2L, 2L, 200.0);
        ExchangePortal exchangePortal = new ExchangePortal(2L, "coinlayer", Currency.BTC, Currency.USD,
                350.0, LocalDateTime.of(2021, 12, 1, 7, 0));
        when(exchangePortalService.findExchangePortalById(2L)).thenReturn(exchangePortal);
        //When
        ItemToBuy itemToBuy = itemToBuyMapper.mapToItemToBuy(itemToBuyDto);
        //Then
        Assert.assertEquals(itemToBuy.getId(), Long.valueOf(1));
        Assert.assertEquals(itemToBuy.getExchangePortal().getId(), Long.valueOf(2));
        Assert.assertEquals(itemToBuy.getQuantityToBuy(), 200.0, 0.001);
    }

}
