package com.cryptoclient.dto;

import com.cryptoclient.domain.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletItemDto {

    private Long id;
    private Long walletId;
    private Currency currency;
    private Double quantity;

    public WalletItemDto(Long walletId, Currency currency, Double quantity) {
        this.walletId = walletId;
        this.currency = currency;
        this.quantity = quantity;
    }
}
