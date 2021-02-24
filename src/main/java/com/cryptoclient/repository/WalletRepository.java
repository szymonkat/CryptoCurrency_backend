package com.cryptoclient.repository;

import com.cryptoclient.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Override
    <S extends Wallet> S save(S Wallet);

    Optional<Wallet> findByName(String name);
}
