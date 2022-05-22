package pt.up.feup.cpm.printerterminal.models

import java.io.Serializable

data class Product(
    var productID: String? = null,
    var name: String? = null,
    var price: Double? = null,
    var characteristics: String? = null,
    var make: String? = null,
    var model: String? = null
): Serializable