package pt.up.feup.cpm.customerapp.models

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.util.*


class Customer {
    private var name: String? = null
    private var address: String? = null
    private var fiscalNumber: String? = null
    private var email: String? = null
    private var password: String? = null
    private var card: Card? = null
    private var publicKey: KeyPair? = null

    constructor(name: String?, address: String?, fiscalNumber: String?,
                 username: String?, password: String?,
                 cardType: String?, cardNumber: String?, cardValidity: String?) {
        this.name = name
        this.address = address
        this.fiscalNumber = fiscalNumber
        this.email = username
        this.password = password
        this.card = Card(cardType, cardNumber, cardValidity)
        this.generateKeyPair()
    }

    private fun generateKeyPair() {
        try {
            val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
            this.publicKey = keyPairGenerator.generateKeyPair()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }

    fun getName(): String? {
        return this.name
    }
}