package pt.up.feup.cpm.customerapp.models

import java.io.Serializable

class Card(
    var type: String? = null,
    var number: String? = null,
    var validity: String? = null
): Serializable