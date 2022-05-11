import mongoose from "mongoose";
import { productSchema } from "./Product";

export const orderSchema = new mongoose.Schema({
    userID: { type: String, required: true },
    content: [
        {
            product: { type: productSchema, required: true },
            quantity: { type: Number, required: true }
        }
    ]
});

let Order = mongoose.model("Order", orderSchema);

export default Order;