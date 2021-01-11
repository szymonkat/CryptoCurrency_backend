package com.cryptoclient.client;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.ExchangePortal;

public interface ApiService {
    public ExchangePortal createExchangePortal(Currency currency);
}
