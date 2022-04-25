package pt.up.feup.cpm.customerapp.models

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.util.*

class CustomerMetaData {
    private var uuid: UUID? = null
    private var name: String? = null
    private var address: String? = null
    private var fiscalNumber: String? = null
    private var username: String? = null
    private var password: String? = null
    private var keyPair: KeyPair? = null

    constructor(name: String?, address: String?, fiscalNumber: String?, username: String?, password: String?) {
        this.uuid = UUID.randomUUID()
        this.name = name
        this.address = address
        this.fiscalNumber = fiscalNumber
        this.username = username
        this.password = password
        this.generateKeyPair()
    }

    private fun generateKeyPair() {
        try {
            val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
            this.keyPair = keyPairGenerator.generateKeyPair()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }

    fun getName(): String? {
        return this.name
    }

    fun getAddress(): String? {
        return this.address
    }

    fun getFiscalNumber(): String? {
        return this.fiscalNumber
    }

    fun getUsername(): String? {
        return this.username
    }

    fun getPassword(): String? {
        return this.password
    }
}