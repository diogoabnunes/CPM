package pt.up.feup.cpm.customerapp.models

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.util.*


class Customer {
    private var uuid: UUID? = null
    private var name: String? = null
    private var address: String? = null
    private var fiscalNumber: String? = null
    private var username: String? = null
    private var password: String? = null
    private var keyPair: KeyPair? = null
    private var cardType: String? = null
    private var cardNumber = 0
    private var cardValidity: Date? = null

    constructor(name: String?, address: String?, fiscalNumber: String?,
                 username: String?, password: String?,
                 cardType: String?, cardNumber: Int, cardValidity: Date?) {
        this.uuid = UUID.randomUUID()
        this.name = name
        this.address = address
        this.fiscalNumber = fiscalNumber
        this.username = username
        this.password = password
        this.generateKeyPair()
        this.cardType = cardType
        this.cardNumber = cardNumber
        this.cardValidity = cardValidity
    }

    private fun generateKeyPair() {
        try {
            val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
            this.keyPair = keyPairGenerator.generateKeyPair()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }
}