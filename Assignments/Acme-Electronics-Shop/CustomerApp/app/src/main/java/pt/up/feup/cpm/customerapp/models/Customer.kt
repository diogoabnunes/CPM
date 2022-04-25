package pt.up.feup.cpm.customerapp.models

import pt.up.feup.cpm.customerapp.interfaces.QRCodeInterface
import java.util.*


class Customer : QRCodeInterface {
    private var metaData: CustomerMetaData? = null
    private var paymentData: CustomerPaymentData? = null

    constructor(name: String?, address: String?, fiscalNumber: String?,
                 username: String?, password: String?,
                 cardType: String?, cardNumber: Int, cardValidity: Date?) {
        this.metaData = CustomerMetaData(name, address, fiscalNumber, username, password)
        this.paymentData = CustomerPaymentData(cardType, cardNumber, cardValidity)
    }

    fun getName(): String? {
        return metaData!!.getName()
    }

    fun getUsername(): String? {
        return metaData!!.getUsername()
    }

    override fun encode(): String? {
        return ""
    }
}