import mongoose from "mongoose";

export const transactionSchema = new mongoose.Schema({
    userID: {
        type: String,
        required: true
    },
    content: [
        {
            product: {
                type: productSchema,
                required: true
            },
            quantity: {
                type: Number,
                required: true
            }
        }
    ],
    date: {
        type: Date,
        required: true
    }
});

let Transaction = mongoose.model("Transaction", transactionSchema);

export default Transaction;