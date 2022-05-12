package pt.up.feup.cpm.customerapp.models

import java.util.*
import kotlin.collections.ArrayList

class Transaction {
    private var transactionID: String? = null
    private var userID: String? = null
    private var content: ArrayList<Pair<Product, Number>>? = null
    private var date: Date? = null
    private var paid: Boolean = false

    constructor(transactionID: String?, userID: String?, content: ArrayList<Pair<Product, Number>>?, date: Date?) {
        this.transactionID = transactionID
        this.userID = userID
        this.content = content
        this.date = date
        this.paid = false
    }

    fun getUserID(): String? {
        return this.userID
    }

    fun getContent(): ArrayList<Pair<Product, Number>>? {
        return this.content
    }

    fun getDate(): Date? {
        return this.date
    }

    fun getPaid(): Boolean {
        return this.paid
    }

    fun payTransaction() {
        this.paid = true
    }
}