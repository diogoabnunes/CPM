package pt.up.feup.dn.lunchlist1

class Restaurant(val name: String, val address: String, val type: String, val notes: String) {
    override fun toString(): String {
        return name
    }
}