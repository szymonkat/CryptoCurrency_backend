package com.cryptoclient.mapper;

import com.cryptoclient.domain.ItemToBuy;
import com.cryptoclient.dto.ItemToBuyDto;
import com.cryptoclient.service.interfaces.ExchangePortalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ItemToBuyMapper {

    private final ExchangePortalService exchangePortalService;

    public ItemToBuy mapToItemToBuy(ItemToBuyDto itemToBuyDto) {
        if (itemToBuyDto.getId() == null) {
            return new ItemToBuy(
                    exchangePortalService.findExchangePortalById(itemToBuyDto.getExchangePortalId()),
                    itemToBuyDto.getQuantityToBuy());
        } else {
            return new ItemToBuy(itemToBuyDto.getId(),
                    exchangePortalService.findExchangePortalById(itemToBuyDto.getExchangePortalId()),
                    itemToBuyDto.getQuantityToBuy());
        }
    }

    public ItemToBuyDto mapToItemToBuyDto(ItemToBuy itemToBuy) {
        if (itemToBuy.getId() == null) {
            return new ItemToBuyDto(
                    itemToBuy.getExchangePortal().getId(),
                    itemToBuy.getQuantityToBuy());
        } else {
            return new ItemToBuyDto(itemToBuy.getId(),
                    itemToBuy.getExchangePortal().getId(),
                    itemToBuy.getQuantityToBuy());
        }
    }

    public List<ItemToBuyDto> mapToItemToBuyDtoList(List<ItemToBuy> itemToBuyList) {
        return itemToBuyList.stream()
                .map(this::mapToItemToBuyDto)
                .collect(Collectors.toList());
    }

    public List<ItemToBuy> mapToItemToBuyList(List<ItemToBuyDto> itemToBuyDtoList) {
        return itemToBuyDtoList.stream()
                .map(this::mapToItemToBuy)
                .collect(Collectors.toList());
    }

}
