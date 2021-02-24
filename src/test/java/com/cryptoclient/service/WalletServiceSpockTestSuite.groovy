/*
package com.cryptoclient.service

import com.cryptoclient.domain.Wallet
import com.cryptoclient.service.interfaces.WalletService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class WalletServiceSpockTestSuite extends spock.lang.Specification {

    @Autowired
    private WalletService walletService

    def setup() {
        def wallet = new Wallet("wallet");
    }

    def "create wallet using service"() {
        when:
            Wallet createdWallet = walletService.createWallet(wallet);
        then:
            createdWallet.getName() == "wallet";
    }

    def "two plus two should equal four"() {
        given:
        int left = 2
        int right = 2

        when:
        int result = left + right

        then:
        result == 4
    }

}
*/
