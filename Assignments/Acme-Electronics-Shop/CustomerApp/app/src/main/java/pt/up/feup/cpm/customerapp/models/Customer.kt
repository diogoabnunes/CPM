package pt.up.feup.cpm.customerapp.models

import java.io.Serializable

data class Customer(
    var userID: String? = null,
    var name: String? = null,
    var address: String? = null,
    var fiscalNumber: String? = null,
    var email: String? = null,
    var password: String? = null,
    var cardType: String? = null,
    var cardNumber: String? = null,
    var cardValidity: String? = null,
    var publicKey: String? = null
): Serializable