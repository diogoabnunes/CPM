package pt.up.feup.cpm.customerapp.models

data class TransactionItem(
    private var transactionItemID: String?,
    private var transactionName: String?,
    private var quantity: Int?,
    private var price: String?
) {

    fun getTransactionItemID(): String?{
        return this.transactionItemID
    }

    fun getTransactionName(): String? {
        return this.transactionName
    }

    fun getQuantity(): Int? {
        return this.quantity
    }

    fun getPrice(): String? {
        return this.price
    }
}