package com.cryproclient.service

import com.cryptoclient.domain.Wallet
import com.cryptoclient.service.interfaces.WalletService
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

class WalletServiceSpockTestSuite extends Specification {

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
