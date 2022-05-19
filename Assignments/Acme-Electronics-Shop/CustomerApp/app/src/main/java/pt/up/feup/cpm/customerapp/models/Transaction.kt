package pt.up.feup.cpm.customerapp.models

import java.io.Serializable

class Transaction(
    var transactionID: String? = null,
    var userID: String? = null,
    var content: MutableList<TransactionItem> = mutableListOf(),
    //private var date: Date? = null
    var date: String? = null,
    var printed: Boolean = false
): Serializable