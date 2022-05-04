const express = require('express');
const router = express.Router();
const db = require('./models/db');

router.get('/customer/add', (req, res) => {
    db.get().collection('customer').find({}).toArray().then((customers) => {
        console.log('Customers', customers)
    })
});