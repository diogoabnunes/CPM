package pt.up.feup.cpm.customerapp.models

import androidx.constraintlayout.motion.widget.TransitionBuilder
import java.io.Serializable
import kotlin.collections.ArrayList

class Transaction : Serializable {
    private var transactionID: String? = null
    private var userID: String? = null
    private var content: ArrayList<Pair<String, Int>> = ArrayList()
    //private var date: Date? = null
    private var date: String? = null
    private var printed: Boolean = false
    private var price: String? = null

    constructor(transactionID: String?, userID: String?, content: ArrayList<Pair<String, Int>>, date: String?, price: String?) {
        this.transactionID = transactionID
        this.userID = userID
        this.content = content
        this.date = date
        this.printed = false
        this.price = price
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

    fun getContent(): List<Pair<String, Int>> {
        return this.content
    }

    fun addItem(productID: String, quantity: Int) {
        this.content.add(Pair<String, Int>(productID, quantity))
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

    fun getPrice(): String? {
        return this.price
    }
}