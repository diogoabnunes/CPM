package pt.up.feup.cpm.customerapp.models

import java.io.Serializable

class TransactionItem(
    var product: Product? = null,
    var quantity: Int? = null
): Serializable{

    fun addQuantity(){
        this.quantity = this.quantity?.plus(1)
    }

    fun removeQuantity() {
        if (this.quantity!! > 0) {
            this.quantity = this.quantity?.minus(1)
        }
    }
}