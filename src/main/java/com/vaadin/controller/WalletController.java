package com.vaadin.controller;

import com.vaadin.domain.WalletItem;
import com.vaadin.dto.WalletDto;
import com.vaadin.dto.WalletItemDto;
import com.vaadin.mapper.WalletItemMapper;
import com.vaadin.mapper.WalletMapper;
import com.vaadin.service.interfaces.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletMapper walletMapper;
    private final WalletService walletService;
    private final WalletItemMapper walletItemMapper;

    @GetMapping
    public List<WalletDto> getAllWallet() {
        return walletMapper.mapToWalletDtoList(walletService.getWallets());
    }

    @GetMapping("{walletId}")
    public WalletDto getWalletById(@PathVariable Long walletId) {
        return walletMapper.mapToWalletDto(walletService.findWalletById(walletId));
    }

    @PostMapping(consumes = "application/json")
    public WalletDto createWallet(@RequestBody WalletDto walletDto) {
        return walletMapper.mapToWalletDto(walletService.createWallet
                (walletMapper.mapToWallet(walletDto)));
    }

    @PutMapping(consumes = "application/json")
    public WalletDto updateWallet(@RequestBody WalletDto walletDto) {
        return walletMapper.mapToWalletDto(walletService.updateWallet
                (walletMapper.mapToWallet(walletDto)));
    }

    @GetMapping("/items/{walletId}")
    public List<WalletItemDto> getWalletItemDtoByWalletId(@PathVariable Long walletId) {
        return walletItemMapper.mapToWalletItemDtoList(walletService.findWalletById(walletId).getWalletItemList());
    }

    @GetMapping("/item/{walletId}")
    public List<WalletItem> getWalletItemByWalletId(@PathVariable Long walletId) {
        return walletService.findWalletById(walletId).getWalletItemList();
    }

    @DeleteMapping("{walletId}")
    public void deleteWallet(@PathVariable Long walletId) {
        walletService.deleteWallet(walletId);
    }

    @GetMapping("/checkUsd/{walletId}")
    public void checkHowManyUsdWalletHas(@PathVariable Long walletId) {
        System.out.println(walletService.checkHowManyUsdWalletHas(walletId));
    }
}
