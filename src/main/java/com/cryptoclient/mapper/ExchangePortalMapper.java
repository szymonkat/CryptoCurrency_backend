package com.cryptoclient.mapper;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.ExchangePortal;
import com.cryptoclient.dto.ExchangePortalDto;
import com.cryptoclient.service.interfaces.ItemToBuyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExchangePortalMapper {

    private final ItemToBuyService itemToBuyService;

    public ExchangePortal mapToExchangePortal(ExchangePortalDto exchangePortalDto) {
        if (exchangePortalDto.getItemToBuyDtoId() == null) {
            return ExchangePortal.builder()
                    .id(exchangePortalDto.getId())
                    .provider(exchangePortalDto.getProvider())
                    .currencyToBuy(exchangePortalDto.getCurrencyToBuy())
                    .currencyToPay(exchangePortalDto.getCurrencyToPay())
                    .ratio(exchangePortalDto.getRatio())
                    .time(exchangePortalDto.getTime())
                    .build();
        } else {
            return ExchangePortal.builder()
                    .id(exchangePortalDto.getId())
                    .provider(exchangePortalDto.getProvider())
                    .currencyToBuy(exchangePortalDto.getCurrencyToBuy())
                    .currencyToPay(exchangePortalDto.getCurrencyToPay())
                    .ratio(exchangePortalDto.getRatio())
                    .time(exchangePortalDto.getTime())
                    .itemToBuy(itemToBuyService.findItemToBuyById(exchangePortalDto.getItemToBuyDtoId()))
                    .build();
        }
    }

    public ExchangePortalDto mapToExchangePortalDto(ExchangePortal exchangePortal) {
        if (exchangePortal.getItemToBuy() == null) {
            return new ExchangePortalDto(exchangePortal.getId(), exchangePortal.getProvider(),
                    exchangePortal.getCurrencyToBuy(),
                    exchangePortal.getCurrencyToPay(), exchangePortal.getRatio(), exchangePortal.getTime());
        } else {
            return new ExchangePortalDto(exchangePortal.getId(), exchangePortal.getProvider(),
                    exchangePortal.getCurrencyToBuy(),
                    exchangePortal.getCurrencyToPay(), exchangePortal.getRatio(), exchangePortal.getTime(),
                    exchangePortal.getItemToBuy().getId());
        }
    }

    public List<ExchangePortalDto> mapToExchangePortalDtoList(List<ExchangePortal> exchangePortalList) {
        return exchangePortalList.stream()
                .map(this::mapToExchangePortalDto)
                .collect(Collectors.toList());
    }

    public List<ExchangePortal> mapToExchangePortalList(List<ExchangePortalDto> exchangePortalDtos) {
        return exchangePortalDtos.stream()
                .map(this::mapToExchangePortal)
                .collect(Collectors.toList());
    }

}
