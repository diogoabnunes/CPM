package pt.up.feup.cpm.customerapp.models

import java.util.*

class CustomerPaymentData {
    private var cardType: String? = null
    private var cardNumber = 0
    private var cardValidity: Date? = null

    constructor(cardType: String?, cardNumber: Int, cardValidity: Date?) {
        this.cardType = cardType
        this.cardNumber = cardNumber
        this.cardValidity = cardValidity
    }
}