package pt.up.feup.dn.lunchlist1

import java.io.Serializable

class Restaurant(val name: String, val address: String, val type: String, val notes: String): Serializable {
    override fun toString(): String {
        return name
    }
}