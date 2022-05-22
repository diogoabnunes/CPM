package pt.up.feup.cpm.printerterminal.models

import java.io.Serializable

class TransactionItem(
    var product: Product? = null,
    var quantity: Int? = null
): Serializable