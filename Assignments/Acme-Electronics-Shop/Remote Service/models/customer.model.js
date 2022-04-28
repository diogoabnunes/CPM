const mongoose = require('mongoose');

var customerSchema = new mongoose.Schema({
    name: {
        type: String,
        required: 'This field is required'
    },
    email: {
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
    username: {
        type: String,
        required: 'This field is required'
    }
});

mongoose.model("Customer", customerSchema)