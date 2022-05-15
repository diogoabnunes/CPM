package pt.up.feup.cpm.printerterminal

import kotlin.collections.ArrayList

class Transaction {
    private var transactionID: String? = null
    private var userID: String? = null
    private var content: ArrayList<Pair<Product, Number>>? = null
    //private var date: Date? = null
    private var date: String? = null
    private var paid: Boolean = false
    private var price: String? = null

    constructor(transactionID: String?, userID: String?, content: ArrayList<Pair<Product, Number>>?, date: String?, price: String?) {
        this.transactionID = transactionID
        this.userID = userID
        this.content = content
        this.date = date
        this.paid = false
        this.price = price
    }

    fun getTransactionId(): String?{
        return this.transactionID
    }

    fun getUserID(): String? {
        return this.userID
    }

    fun getContent(): ArrayList<Pair<Product, Number>>? {
        return this.content
    }

    fun getDate(): String? {
        return this.date
    }

    fun getPaid(): Boolean {
        return this.paid
    }

    fun payTransaction() {
        this.paid = true
    }

    fun getPrice(): String? {
        return this.price
    }
}