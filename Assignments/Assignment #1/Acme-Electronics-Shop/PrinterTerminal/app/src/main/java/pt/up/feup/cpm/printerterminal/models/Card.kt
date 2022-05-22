package pt.up.feup.cpm.printerterminal.models

import java.io.Serializable

class Card(
    var type: String? = null,
    var number: String? = null,
    var validity: String? = null
): Serializable