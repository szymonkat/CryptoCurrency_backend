package com.cryptoclient.controller;

import com.cryptoclient.client.ApiService;
import com.cryptoclient.client.ServiceFactory;
import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.ExchangePortal;
import com.cryptoclient.dto.ExchangePortalDto;
import com.cryptoclient.mapper.ExchangePortalMapper;
import com.cryptoclient.service.interfaces.ExchangePortalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/exchange")
@RequiredArgsConstructor
public class ExchangePortalController {

    private final ServiceFactory serviceFactory;
    private final ExchangePortalMapper exchangePortalMapper;
    private final ExchangePortalService exchangePortalService;

    @PostMapping
    public ExchangePortalDto createNewResponse(@RequestParam Currency currency, @RequestParam String serviceName) {
        ApiService apiService = serviceFactory.createService(serviceName);
        ExchangePortal exchangePortal = apiService.createExchangePortal(currency);
        exchangePortalService.save(exchangePortal);
        return exchangePortalMapper.mapToExchangePortalDto(exchangePortal);
    }

    @GetMapping
    public List<ExchangePortalDto> getExchangePortals() {
        return exchangePortalMapper.mapToExchangePortalDtoList(exchangePortalService.getExchangePortals());
    }

    @GetMapping("/id/{exchangePortalId}")
    public ExchangePortalDto getExchangePortalById(@PathVariable Long exchangePortalId) {
        return exchangePortalMapper.mapToExchangePortalDto(exchangePortalService.findExchangePortalById(exchangePortalId));
    }

    @DeleteMapping("/{exchangePortalId}")
    public void deleteExchangePortal(@PathVariable Long exchangePortalId) {
        exchangePortalService.delete(exchangePortalId);
    }

    @GetMapping("/currency")
    public List<ExchangePortalDto> getExchangePortalsWithCurrency(@RequestParam Currency currency) {
        return exchangePortalMapper.mapToExchangePortalDtoList(exchangePortalService.getExchangePortalsWithCurrency(currency));
    }
}
