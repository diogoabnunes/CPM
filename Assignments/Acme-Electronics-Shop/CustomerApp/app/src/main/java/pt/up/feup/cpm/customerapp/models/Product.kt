package pt.up.feup.cpm.customerapp.models

class Product {
    private var productID: String? = null
    private var name: String? = null
    private var price = 0.0
    private var characteristics: String? = null
    private var make: String? = null
    private var model: String? = null

    constructor(productID: String?, name: String?, price: Double, characteristics: String?, make: String?, model: String?) {
        this.productID = productID
        this.name = name
        this.price = price
        this.characteristics = characteristics
        this.make = make
        this.model = model
    }

    fun getProductID() : String? {
        return this.productID
    }

    fun getName() : String? {
        return this.name
    }

    fun getPrice() : Double? {
        return this.price
    }

    fun getCharacteristics() : String? {
        return this.characteristics
    }

    fun getMake() : String? {
        return this.make
    }

    fun getModel() : String? {
        return this.model
    }
}