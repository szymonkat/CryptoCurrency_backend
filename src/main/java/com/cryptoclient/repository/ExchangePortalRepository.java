package com.cryptoclient.repository;

import com.cryptoclient.domain.ExchangePortal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangePortalRepository extends JpaRepository<ExchangePortal, Long> {

    @Override
    <S extends ExchangePortal> S save(S ExchangePortal);

}
