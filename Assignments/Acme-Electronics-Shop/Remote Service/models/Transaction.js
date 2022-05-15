import mongoose from "mongoose";
import { productSchema } from "./Product.js";

export const transactionSchema = new mongoose.Schema({
    transactionID: { type: String, required: true },
    userID: { type: String, required: true },
    content: [
        {
            product: { type: productSchema, required: true },
            quantity: { type: Number, required: true }
        }
    ],
    date: { type: Date, default: Date.now },
    printed: { type: Boolean, required: true }
});

let Transaction = mongoose.model("Transaction", transactionSchema);

export default Transaction;