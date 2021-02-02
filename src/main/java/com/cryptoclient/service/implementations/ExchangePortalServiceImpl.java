package com.cryptoclient.service.implementations;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.ExchangePortal;
import com.cryptoclient.exceptions.NotFoundException;
import com.cryptoclient.repository.ExchangePortalRepository;
import com.cryptoclient.service.interfaces.ExchangePortalService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ExchangePortalServiceImpl implements ExchangePortalService {

    private final ExchangePortalRepository exchangePortalRepository;

    @Override
    public ExchangePortal findExchangePortalById(Long exchangePortalId) throws NotFoundException {
        return exchangePortalRepository.findById(exchangePortalId)
                .orElseThrow(() -> new NotFoundException("Exchange Portal with id: " + exchangePortalId + " does not exist"));
    }

    @Override
    public List<ExchangePortal> getExchangePortals() {
        return exchangePortalRepository.findAll();
    }

    @Override
    public ExchangePortal save(ExchangePortal exchangePortal) {
        return exchangePortalRepository.save(exchangePortal);
    }

    @Override
    public List<ExchangePortal> getExchangePortalsWithCurrency(Currency currency) {
        return getExchangePortals().stream()
                .filter(n -> n.getCurrencyToBuy() == currency)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long exchangePortalId) {
        findExchangePortalById(exchangePortalId);
        exchangePortalRepository.deleteById(exchangePortalId);
    }

}
