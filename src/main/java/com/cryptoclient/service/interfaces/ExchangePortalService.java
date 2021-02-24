package com.cryptoclient.service.interfaces;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.ExchangePortal;

import java.util.List;

public interface ExchangePortalService {
    ExchangePortal findExchangePortalById(final Long exchangePortalId);

    List<ExchangePortal> getExchangePortals();

    List<ExchangePortal> getExchangePortalsWithCurrency(final Currency currency);

    ExchangePortal save(final ExchangePortal ExchangePortal);

    void delete(final Long exchangePortalId);
}
