import mongoose from "mongoose";

export const customerSchema = new mongoose.Schema({
    userID: { type: String, required: true },
    name: { type: String, required: true },
    address: { type: String, required: true },
    fiscalNumber: { type: Number, required: true },
    email: { 
        type: String,
        unique: true,
        required: true, 
        match: /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    },
    password: { type: String, required: true },
    cardType: { type: String, required: true },
    cardNumber: { type: String, required: true },
    cardValidity: { type: String, required: true },
    publicKey: { type: String, required: true }
});

let Customer = mongoose.model("Customer", customerSchema);

export default Customer;