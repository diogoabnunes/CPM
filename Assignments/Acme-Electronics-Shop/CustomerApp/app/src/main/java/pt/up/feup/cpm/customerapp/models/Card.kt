package pt.up.feup.cpm.customerapp.models

import java.util.*

class Card {
    private var type: String? = null
    private var number = 0
    private var validity: Date? = null

    constructor(type: String?, number: Int, validity: Date?) {
        this.type = type
        this.number = number
        this.validity = validity
    }
}