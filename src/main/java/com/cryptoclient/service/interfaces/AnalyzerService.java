package com.cryptoclient.service.interfaces;

import com.cryptoclient.domain.Currency;
import com.cryptoclient.domain.ExchangePortal;

public interface AnalyzerService {
    ExchangePortal findMinRatio(Currency currency);

    ExchangePortal findMaxRatio(Currency currency);

    ExchangePortal findOldestRatio(Currency currency);

    ExchangePortal findNewestRatio(Currency currency);
}
