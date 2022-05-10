package pt.up.feup.cpm.customerapp.models

import java.util.*

class Card {
    private var type: String? = null
    private var number: String? = null
    private var validity: String? = null

    constructor(type: String?, number: String?, validity: String?) {
        this.type = type
        this.number = number
        this.validity = validity
    }

    fun getType(): String? {
        return this.type
    }

    fun getNumber(): String? {
        return this.number
    }

    fun getValidity(): String? {
        return this.validity
    }
}