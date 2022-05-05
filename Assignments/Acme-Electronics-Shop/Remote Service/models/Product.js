import mongoose from "mongoose";

export const productSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true
    },
    price: {
        type: Number,
        required: true
    },
    make: {
        type: String,
        required: true
    },
    model: {
        type: String,
        required: true
    },
    characteristics: {
        type: String,
        required: true
    }
});

let Product = mongoose.model("Product", productSchema);

export default Product;