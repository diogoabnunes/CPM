package pt.up.feup.cpm.customerapp.models

import java.util.*

class Transaction {
    private var customer: Customer? = null
    private var total = 0.0
    private var date: Date? = null

    constructor(customer: Customer?, total: Double, date: Date?) {
        this.customer = customer
        this.total = total
        this.date = date
    }

    fun getCustomer(): Customer? {
        return this.customer
    }

    fun getTotal(): Double {
        return this.total
    }

    fun getDate(): Date? {
        return this.date
    }
}