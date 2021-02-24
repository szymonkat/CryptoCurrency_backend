package com.cryptoclient.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "WALLET_ITEMS")
public class WalletItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne//(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Column
    private Currency currency;

    @Column
    private Double quantity;


    public WalletItem(@NotNull Wallet wallet, Currency currency, Double quantity) {
        this.wallet = wallet;
        this.currency = currency;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WalletItem that = (WalletItem) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!wallet.equals(that.wallet)) return false;
        if (currency != that.currency) return false;
        return quantity.equals(that.quantity);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + wallet.hashCode();
        result = 31 * result + currency.hashCode();
        result = 31 * result + quantity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "WalletItem{" +
                "id=" + id +
                ", wallet=" + wallet +
                ", currency=" + currency +
                ", quantity=" + quantity +
                '}';
    }
}
