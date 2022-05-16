package pt.up.feup.cpm.customerapp.models

import java.io.Serializable

class TransactionItem : Serializable {
    private var product: Product? = null
    private var quantity: Int? = null

    constructor(product: Product?, quantity: Int?) {
        this.product = product
        this.quantity = quantity
    }

    fun getProduct(): Product? {
        return this.product
    }

    fun getQuantity(): Int? {
        return this.quantity
    }

    fun setQuantity(quantity: Int?){
        this.quantity = quantity
    }

    fun addQuantity(){
        this.quantity = this.quantity?.plus(1)
    }

    fun removeQuantity() {
        if (this.quantity!! > 0) {
            this.quantity = this.quantity?.minus(1)
        }
    }
}