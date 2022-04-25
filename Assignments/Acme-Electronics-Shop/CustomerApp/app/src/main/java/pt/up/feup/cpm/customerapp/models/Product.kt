package pt.up.feup.cpm.customerapp.models

class Product {
    private var name: String? = null
    private var price = 0.0
    private var characteristics: String? = null
    private var make: String? = null
    private var model: String? = null

    constructor(name: String?, price: Double, characteristics: String?, make: String?, model: String?) {
        this.name = name
        this.price = price
        this.characteristics = characteristics
        this.make = make
        this.model = model
    }
}