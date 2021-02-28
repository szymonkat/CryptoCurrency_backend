package com.cryproclient.service

import com.cryptoclient.domain.Wallet
import com.cryptoclient.repository.WalletRepository
import com.cryptoclient.service.interfaces.WalletService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class WalletServiceSpec extends Specification {

    @Autowired
    private final WalletService walletService;

    def "two plus two should equal four"() {
        given:
        int left = 2
        int right = 2

        when:
        int result = left + right

        then:
        result == 4
    }

/*    def "Find wallet by id"() {
        given:
        Wallet wallet = new Wallet("test_wallet_1");

        when:
        Wallet createdWallet = walletService.createWallet(wallet);

        then:
        1 * createdWallet.getName() == "test_wallet_1";

    }*/


}
