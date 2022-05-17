package pt.up.feup.cpm.customerapp.models

import java.io.Serializable

class TransactionItem(
    var product: Product? = null,
    var quantity: Int? = null
): Serializable