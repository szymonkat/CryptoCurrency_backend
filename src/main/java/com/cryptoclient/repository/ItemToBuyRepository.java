package com.cryptoclient.repository;

import com.cryptoclient.domain.ItemToBuy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemToBuyRepository extends JpaRepository<ItemToBuy, Long> {

    @Override
    <S extends ItemToBuy> S save(S ItemToBuy);

}
