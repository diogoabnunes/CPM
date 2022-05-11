import mongoose from "mongoose";

export const productSchema = new mongoose.Schema({
    productID: { type: String, required: true },
    name: { type: String, required: true },
    price: { type: Number, required: false },
    make: { type: String, required: false },
    model: { type: String, required: false },
    characteristics: { type: String, required: false }
});

let Product = mongoose.model("Product", productSchema);

export default Product;