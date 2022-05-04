const mongoose = require('mongoose');

var customerSchema = new mongoose.Schema({
    userID: {
        type: String,
        required: 'This field is required'
    },
    name: {
        type: String,
        required: 'This field is required'
    },
    address: {
        type: String,
        required: 'This field is required'
    },
    fiscalNumber: {
        type: Number,
        required: 'This field is required'
    },
    email: {
        type: String,
        required: 'This field is required'
    },
    password: {
        type: String,
        required: 'This field is required'
    },
    publicKey: {
        type: String,
        required: 'This field is required'
    },
    cardType: {
        type: String,
        required: 'This field is required'
    },
    cardNumber: {
        type: String,
        required: 'This field is required'
    },
    cardValidity: {
        type: String,
        required: 'This field is required'
    }
});

mongoose.model("Customer", customerSchema)