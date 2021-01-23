package com.cryptoclient.controller;

import com.cryptoclient.dto.WalletDto;
import com.cryptoclient.mapper.WalletMapper;
import com.cryptoclient.service.interfaces.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletMapper walletMapper;
    private final WalletService walletService;

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
        return walletMapper.mapToWalletDto(walletService.updateWalletName
                (walletMapper.mapToWallet(walletDto)));
    }

    @DeleteMapping("{walletId}")
    public void deleteWallet(@PathVariable Long walletId) {
        walletService.deleteWallet(walletId);
    }

}
