package pt.up.feup.cpm.customerapp.models

import androidx.constraintlayout.motion.widget.TransitionBuilder
import java.io.Serializable
import kotlin.collections.ArrayList

class Transaction : Serializable {
    private var transactionID: String? = null
    private var userID: String? = null
    private var content: MutableList<TransactionItem> = mutableListOf()
    //private var date: Date? = null
    private var date: String? = null
    private var printed: Boolean = false

    constructor(transactionID: String?, userID: String?, content: MutableList<TransactionItem>, date: String?) {
        this.transactionID = transactionID
        this.userID = userID
        this.content = content
        this.date = date
        this.printed = false
    }

    constructor() { }

    fun getTransactionId(): String? {
        return this.transactionID
    }

    fun getUserID(): String? {
        return this.userID
    }

    fun setUserID(userID: String) {
        this.userID = userID
    }

    fun getContent(): MutableList<TransactionItem>? {
        return this.content
    }

    fun setContent(content: MutableList<TransactionItem>) {
        this.content = content
    }

    fun addItem(product: Product?, quantity: Int?) {
        this.content.add(TransactionItem(product, quantity))
    }

    fun addItem(transactionItem: TransactionItem) {
        this.content.add(transactionItem)
    }

    fun getDate(): String? {
        return this.date
    }

    fun getPrinted(): Boolean {
        return this.printed
    }

    fun printedTransaction() {
        this.printed = true
    }
}