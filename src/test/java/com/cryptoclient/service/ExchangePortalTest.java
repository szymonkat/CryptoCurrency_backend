package com.cryptoclient.service;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.ExchangePortal;
import com.cryptoclient.repository.ExchangePortalRepository;
import com.cryptoclient.service.interfaces.ExchangePortalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class ExchangePortalTest {

    ExchangePortal exchangePortal = ExchangePortal.builder()
            .provider("nomics")
            .currencyToBuy(Currency.XMR)
            .currencyToPay(Currency.USD)
            .ratio(10.0)
            .time(LocalDateTime.of(2021, 12, 2, 6, 23))
            .build();

    ExchangePortal exchangePortal1 = ExchangePortal.builder()
            .provider("nomics")
            .currencyToBuy(Currency.BTC)
            .currencyToPay(Currency.USD)
            .ratio(10.0)
            .time(LocalDateTime.of(2021, 12, 2, 6, 23))
            .build();

    ExchangePortal exchangePortal2 = ExchangePortal.builder()
            .provider("nomics")
            .currencyToBuy(Currency.BTC)
            .currencyToPay(Currency.USD)
            .ratio(10.0)
            .time(LocalDateTime.of(2021, 12, 2, 6, 23))
            .build();

    @Autowired
    private ExchangePortalService exchangePortalService;
    @Autowired
    private ExchangePortalRepository exchangePortalRepository;

    @Test
    public void shouldFindExchangePortalById() {
        //Given
        exchangePortalService.save(exchangePortal);
        Long exchangePortalId = exchangePortal.getId();
        //When
        ExchangePortal foundExchangePortal = exchangePortalService.findExchangePortalById(exchangePortalId);
        //Then
        assertEquals(foundExchangePortal.getProvider(), "nomics");
        assertEquals(foundExchangePortal.getCurrencyToBuy(), Currency.XMR);
        assertEquals(foundExchangePortal.getCurrencyToPay(), Currency.USD);
        assertEquals(foundExchangePortal.getRatio(), 10.0, 0.001);
        assertEquals(foundExchangePortal.getTime(), LocalDateTime.of(2021, 12, 2, 6, 23));
        //Clean-Up
        exchangePortalRepository.deleteById(exchangePortalId);
    }

    @Test
    public void shouldGetExchangePortals() {
        //Given
        exchangePortalService.save(exchangePortal);
        Long exchangePortalId = exchangePortal.getId();
        List<ExchangePortal> exchangePortalList = new ArrayList<>();
        //When
        exchangePortalList = exchangePortalService.getExchangePortals();
        //Then
        assertTrue(!exchangePortalList.isEmpty());
        //Clean-Up
        exchangePortalRepository.deleteById(exchangePortalId);
    }

    @Test
    public void shouldSaveExchangePortals() {
        //Given
        exchangePortalService.save(exchangePortal);
        Long exchangePortalId = exchangePortal.getId();
        //When
        ExchangePortal foundExchangePortal = exchangePortalService.findExchangePortalById(exchangePortalId);
        //Then
        assertEquals(foundExchangePortal.getProvider(), "nomics");
        assertEquals(foundExchangePortal.getCurrencyToBuy(), Currency.XMR);
        assertEquals(foundExchangePortal.getCurrencyToPay(), Currency.USD);
        assertEquals(foundExchangePortal.getRatio(), 10.0, 0.001);
        assertEquals(foundExchangePortal.getTime(), LocalDateTime.of(2021, 12, 2, 6, 23));
        //Clean-Up
        exchangePortalRepository.deleteById(exchangePortalId);
    }

    @Test
    public void shouldGetExchangePortalsWithCurrency() {
        //Given
        exchangePortalService.save(exchangePortal);
        Long exchangePortalId = exchangePortal.getId();

        exchangePortalService.save(exchangePortal1);
        Long exchangePortalId1 = exchangePortal1.getId();

        exchangePortalService.save(exchangePortal2);
        Long exchangePortalId2 = exchangePortal2.getId();
        List<ExchangePortal> exchangePortalList = new ArrayList<>();
        //When
        exchangePortalList = exchangePortalService.getExchangePortalsWithCurrency(Currency.BTC);
        //Then
        List<ExchangePortal> exchangePortalFound = exchangePortalList.stream()
                .filter(n -> n.getId() == exchangePortalId1 || n.getId() == exchangePortalId2)
                .collect(Collectors.toList());

        assertEquals(2, exchangePortalFound.size());
        //Clean-Up
        exchangePortalRepository.deleteById(exchangePortalId);
        exchangePortalRepository.deleteById(exchangePortalId1);
        exchangePortalRepository.deleteById(exchangePortalId2);
    }

    @Test
    public void shouldDeleteExchangePortal() {
        //Given
        exchangePortalService.save(exchangePortal);
        Long exchangePortalId = exchangePortal.getId();
        //When
        exchangePortalService.delete(exchangePortalId);
        //Then
        Boolean checkIfExists = exchangePortalRepository.existsById(exchangePortalId);
        assertFalse(checkIfExists);
    }

}
