const express = require('express');
var router = express.Router();
const mongoose = require('mongoose');
const Customer = mongoose.model("Customer");

router.get('/', (req, res) => {
    res.render('customer/addOrEdit', {
        viewTitle: 'Insert Customer'
    })
});

router.post('/', (req, res) => {
    if (req.body._id == '') {
        insertRecord(req, res)
    } else {
        updateRecord(req, res)
    }
});

function insertRecord(req, res) {
    var customer = new Customer()
    customer.userID = req.body.userID
    customer.name = req.body.name
    customer.address = req.body.address
    customer.fiscalNumber = req.body.fiscalNumber
    customer.email = req.body.email
    customer.password = req.body.password
    customer.publicKey = req.body.publicKey
    customer.cardType = req.body.cardType
    customer.cardNumber = req.body.cardNumber
    customer.cardValidity = req.body.cardValidity
    customer.save((err, doc) => {
        if (!err) {
            res.redirect('customer/list');
        } else {
            console.log('Error during insert: ' + err);
        }
    });
}

function updateRecord(req, res) {
    Customer.findOneAndUpdate(
        {_id: req.body._id},
        req.body,
        {new: true},
        (err, doc) => {
            if (!err) {
                res.redirect('customer/list');
            } else {
                console.log('Error during update: ' + err);
            }
        }
    );
}

router.get('/list', (req, res) => {
    Customer.find((err, docs) => {
        if (!err) {
            res.render('customer/list', {
                list: docs
            })
        } else {
            console.log('Error in retrieval: ' + err);
        }
    });
})

router.get('/:id', (req, res) => {
    Customer.findById(req.params.id, (err, doc) => {
        if (!err) {
            res.render('customer/addOrEdit', {
                viewTitle: 'Update Customer',
                customer: doc,
            })
            console.log(doc);
        }
    });
})

router.get('/delete/:id', (req, res) => {
    Customer.findByIdAndRemove(req.params.id, (err, doc) => {
        if (!err) {
            res.redirect("/customer/list");
        } else {
            console.log("Error in deletion" + err);
        }
    });
});

module.exports = router