import mongoose from "mongoose";

export const transactionSchema = new mongoose.Schema({
    transactionID: { type: String, required: true },
    userID: { type: String, required: true },
    content: [
        {
            productID: { type: String, required: true },
            quantity: { type: Number, required: true }
        }
    ],
    date: { type: Date, default: Date.now },
    paid: { type: Boolean, required: true }
});

let Transaction = mongoose.model("Transaction", transactionSchema);

export default Transaction;

// não sei como colocar no pedido o content,
// sendo q é uma lista de [produto + quantity]