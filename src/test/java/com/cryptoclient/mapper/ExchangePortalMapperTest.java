package com.cryptoclient.mapper;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.ExchangePortal;
import com.cryptoclient.dto.ExchangePortalDto;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ExchangePortalMapperTest {

    @InjectMocks
    private ExchangePortalMapper exchangePortalMapper;

    @Test
    public void shouldMapToExchangePortal() {
        //Given
        ExchangePortalDto exchangePortalDto = new ExchangePortalDto(1L, "nomics", Currency.XMR, Currency.USD,
                300.0, LocalDateTime.of(2021, 12, 1, 7, 0));
        //When
        ExchangePortal exchangePortal = exchangePortalMapper.mapToExchangePortal(exchangePortalDto);
        //Then
        Assert.assertEquals(exchangePortal.getId(), Long.valueOf(1));
        Assert.assertEquals(exchangePortal.getProvider(), "nomics");
        Assert.assertEquals(exchangePortal.getCurrencyToBuy(), Currency.XMR);
        Assert.assertEquals(exchangePortal.getCurrencyToPay(), Currency.USD);
        Assert.assertEquals(exchangePortal.getRatio(), 300.0, 0.001);
        Assert.assertEquals(exchangePortal.getTime(), LocalDateTime.of(2021, 12, 1, 7, 0));
    }

    @Test
    public void shouldMapToExchangePortalDto() {
        //Given
        ExchangePortal exchangePortal = ExchangePortal.builder()
                .id(2L)
                .provider("coinLayer")
                .currencyToBuy(Currency.BTC)
                .currencyToPay(Currency.USD)
                .ratio(350.0)
                .time(LocalDateTime.of(2021, 12, 1, 7, 0))
                .build();
        //When
        ExchangePortalDto exchangePortalDto = exchangePortalMapper.mapToExchangePortalDto(exchangePortal);
        //Then
        Assert.assertEquals(exchangePortalDto.getId(), Long.valueOf(2));
        Assert.assertEquals(exchangePortalDto.getProvider(), "coinLayer");
        Assert.assertEquals(exchangePortalDto.getCurrencyToBuy(), Currency.BTC);
        Assert.assertEquals(exchangePortalDto.getCurrencyToPay(), Currency.USD);
        Assert.assertEquals(exchangePortalDto.getRatio(), 350.0, 0.001);
        Assert.assertEquals(exchangePortalDto.getTime(), LocalDateTime.of(2021, 12, 1, 7, 0));
    }

    @Test
    public void shouldMapToExchangePortalsList() {
        //Given
        ExchangePortalDto exchangePortalDto1 = new ExchangePortalDto(1L, "nomics", Currency.XMR, Currency.USD,
                300.0, LocalDateTime.of(2021, 12, 1, 7, 0));
        ExchangePortalDto exchangePortalDto2 = new ExchangePortalDto(2L, "coinlayer", Currency.BTC, Currency.USD,
                350.0, LocalDateTime.of(2021, 12, 1, 7, 0));
        List<ExchangePortalDto> exchangePortalDtoList = new ArrayList<>();
        exchangePortalDtoList.add(exchangePortalDto1);
        exchangePortalDtoList.add(exchangePortalDto2);

        //When
        List<ExchangePortal> exchangePortalList = exchangePortalMapper.mapToExchangePortalList(exchangePortalDtoList);
        //Then
        Assert.assertEquals(exchangePortalList.get(0).getId(), Long.valueOf(1));
        Assert.assertEquals(exchangePortalList.get(0).getProvider(), "nomics");
        Assert.assertEquals(exchangePortalList.get(0).getCurrencyToBuy(), Currency.XMR);
        Assert.assertEquals(exchangePortalList.get(0).getCurrencyToPay(), Currency.USD);
        Assert.assertEquals(exchangePortalList.get(0).getRatio(), 300.0, 0.001);
        Assert.assertEquals(exchangePortalList.get(0).getTime(), LocalDateTime.of(2021, 12, 1, 7, 0));
        Assert.assertEquals(exchangePortalList.get(1).getId(), Long.valueOf(2));
        Assert.assertEquals(exchangePortalList.get(1).getProvider(), "coinlayer");
        Assert.assertEquals(exchangePortalList.get(1).getCurrencyToBuy(), Currency.BTC);
        Assert.assertEquals(exchangePortalList.get(1).getCurrencyToPay(), Currency.USD);
        Assert.assertEquals(exchangePortalList.get(1).getRatio(), 350.0, 0.001);
        Assert.assertEquals(exchangePortalList.get(1).getTime(), LocalDateTime.of(2021, 12, 1, 7, 0));
    }

    @Test
    public void shouldMapToExchangePortalsDtoList() {
        //Given
        ExchangePortal exchangePortal1 = ExchangePortal.builder()
                .id(1L)
                .provider("nomics")
                .currencyToBuy(Currency.XMR)
                .currencyToPay(Currency.USD)
                .ratio(300.0)
                .time(LocalDateTime.of(2021, 12, 1, 7, 0))
                .build();
        ExchangePortal exchangePortal2 = ExchangePortal.builder()
                .id(2L)
                .provider("coinLayer")
                .currencyToBuy(Currency.BTC)
                .currencyToPay(Currency.USD)
                .ratio(350.0)
                .time(LocalDateTime.of(2021, 12, 1, 7, 0))
                .build();

        List<ExchangePortal> exchangePortalList = new ArrayList<>();
        exchangePortalList.add(exchangePortal1);
        exchangePortalList.add(exchangePortal2);

        //When
        List<ExchangePortalDto> exchangePortalDtoList = exchangePortalMapper.mapToExchangePortalDtoList(exchangePortalList);
        //Then
        Assert.assertEquals(exchangePortalDtoList.get(0).getId(), Long.valueOf(1));
        Assert.assertEquals(exchangePortalDtoList.get(0).getProvider(), "nomics");
        Assert.assertEquals(exchangePortalDtoList.get(0).getCurrencyToBuy(), Currency.XMR);
        Assert.assertEquals(exchangePortalDtoList.get(0).getCurrencyToPay(), Currency.USD);
        Assert.assertEquals(exchangePortalDtoList.get(0).getRatio(), 300.0, 0.001);
        Assert.assertEquals(exchangePortalDtoList.get(0).getTime(), LocalDateTime.of(2021, 12, 1, 7, 0));
        Assert.assertEquals(exchangePortalDtoList.get(1).getId(), Long.valueOf(2));
        Assert.assertEquals(exchangePortalDtoList.get(1).getProvider(), "coinLayer");
        Assert.assertEquals(exchangePortalDtoList.get(1).getCurrencyToBuy(), Currency.BTC);
        Assert.assertEquals(exchangePortalDtoList.get(1).getCurrencyToPay(), Currency.USD);
        Assert.assertEquals(exchangePortalDtoList.get(1).getRatio(), 350.0, 0.001);
        Assert.assertEquals(exchangePortalDtoList.get(1).getTime(), LocalDateTime.of(2021, 12, 1, 7, 0));
    }


}
