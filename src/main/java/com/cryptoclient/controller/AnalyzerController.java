package com.cryptoclient.controller;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.dto.ExchangePortalDto;
import com.cryptoclient.mapper.ExchangePortalMapper;
import com.cryptoclient.service.interfaces.AnalyzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/analyzer")
@RequiredArgsConstructor
public class AnalyzerController {

    private final AnalyzerService analyzerService;
    private final ExchangePortalMapper exchangePortalMapper;

    @GetMapping(value = "/min")
    public ExchangePortalDto findMin(@RequestParam Currency currency) {
        return exchangePortalMapper.mapToExchangePortalDto(analyzerService.findMinRatio(currency));
    }

    @GetMapping(value = "/max")
    public ExchangePortalDto findMax(@RequestParam Currency currency) {
        return exchangePortalMapper.mapToExchangePortalDto(analyzerService.findMaxRatio(currency));
    }

    @GetMapping(value = "/old")
    public ExchangePortalDto findOldest(@RequestParam Currency currency) {
        return exchangePortalMapper.mapToExchangePortalDto(analyzerService.findOldestRatio(currency));
    }

    @GetMapping(value = "/young")
    public ExchangePortalDto findYoungest(@RequestParam Currency currency) {
        return exchangePortalMapper.mapToExchangePortalDto(analyzerService.findNewestRatio(currency));
    }


}
