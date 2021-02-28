package com.cryptoclient.service;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.ExchangePortal;
import com.cryptoclient.repository.ExchangePortalRepository;
import com.cryptoclient.service.interfaces.AnalyzerService;
import com.cryptoclient.service.interfaces.ExchangePortalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AnalyzerTest {

    ExchangePortal exchangePortalNewest = ExchangePortal.builder()
            .provider("nomics")
            .currencyToBuy(Currency.XMR)
            .currencyToPay(Currency.USD)
            .ratio(300.0)
            .time(LocalDateTime.of(2100, 12, 2, 6, 23))
            .build();

    ExchangePortal exchangePortalOldest = ExchangePortal.builder()
            .provider("nomics")
            .currencyToBuy(Currency.XMR)
            .currencyToPay(Currency.USD)
            .ratio(300.0)
            .time(LocalDateTime.of(1800, 12, 2, 6, 23))
            .build();

    ExchangePortal exchangePortalMax = ExchangePortal.builder()
            .provider("nomics")
            .currencyToBuy(Currency.XMR)
            .currencyToPay(Currency.USD)
            .ratio(10000000000.0)
            .time(LocalDateTime.of(1800, 12, 2, 6, 23))
            .build();

    ExchangePortal exchangePortalMin = ExchangePortal.builder()
            .provider("nomics")
            .currencyToBuy(Currency.XMR)
            .currencyToPay(Currency.USD)
            .ratio(0.0001)
            .time(LocalDateTime.of(2021, 12, 2, 6, 23))
            .build();

    @Autowired
    private AnalyzerService analyzerService;
    @Autowired
    private ExchangePortalService exchangePortalService;
    @Autowired
    private ExchangePortalRepository exchangePortalRepository;

    @Test
    public void shouldReturnMinRatio() {
        //Given
        exchangePortalService.save(exchangePortalMin);
        Long exchangePortalId = exchangePortalMin.getId();
        //When
        ExchangePortal exchangePortal = analyzerService.findMinRatio(Currency.XMR);
        assertEquals(exchangePortal.getRatio(), 0.0001, 0.0001);
        //Clean-up
        exchangePortalRepository.deleteById(exchangePortalId);
    }

    @Test
    public void shouldReturnMaxRatio() {
        //Given
        exchangePortalService.save(exchangePortalMax);
        Long exchangePortalId = exchangePortalMax.getId();
        //When
        ExchangePortal exchangePortal = analyzerService.findMaxRatio(Currency.XMR);
        assertEquals(exchangePortal.getRatio(), 10000000000.0, 0.0001);
        //Clean-up
        exchangePortalRepository.deleteById(exchangePortalId);
    }


    @Test
    public void shouldReturnOldestRatio() {
        //Given
        exchangePortalService.save(exchangePortalOldest);
        Long exchangePortalId = exchangePortalOldest.getId();
        //When
        ExchangePortal exchangePortal = analyzerService.findOldestRatio(Currency.XMR);
        assertEquals(exchangePortal.getTime(), LocalDateTime.of(1800, 12, 2, 6, 23));
        //Clean-up
        exchangePortalRepository.deleteById(exchangePortalId);
    }

    @Test
    public void shouldReturnNewestRatio() {
        //Given
        exchangePortalService.save(exchangePortalNewest);
        Long exchangePortalId = exchangePortalNewest.getId();
        //When
        ExchangePortal exchangePortal = analyzerService.findNewestRatio(Currency.XMR);
        assertEquals(exchangePortal.getTime(), LocalDateTime.of(2100, 12, 2, 6, 23));
        //Clean-up
        exchangePortalRepository.deleteById(exchangePortalId);
    }
}
