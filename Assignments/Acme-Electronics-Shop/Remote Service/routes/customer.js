import express from "express";
const router = express.Router();
import Customer from "../models/Customer.js";
import bcrypt from "bcrypt";
import crypto from "crypto";

router.get('/get-all', async (req, res) => {
    try {
        const customers = await Customer.find({});
        res.status(200).json(customers);
    }
    catch (error) {
        res.status(400).json({message: error.message});
    }
});

router.get('/get/:email', async (req, res) => {
    try {
        const customers = await Customer.findOne({email: req.params.email});
        res.status(200).json(customers);
    }
    catch (error) {
        res.status(400).json({message: error.message})
    }
})

router.post('/register', async (req, res) => {
    const hashedPassword = await bcrypt.hash(req.body.password, 10);
    const newCustomer = await new Customer(
        {userID: crypto.randomUUID(),
        name: req.body.name,
        address: req.body.address,
        fiscalNumber: req.body.fiscalNumber,
        email: req.body.email,
        password: hashedPassword,
        cardType: req.body.cardType,
        cardNumber: req.body.cardNumber,
        cardValidity: req.body.cardValidity,
        publicKey: req.body.publicKey
    });

    try {
        const data = await newCustomer.save();
        res.status(200).json(data);
    }
    catch (error) {
        res.status(400).json({message: error.message})
    }
});

router.post('/login', async (req, res) => {
    const customer = await Customer.find({email: req.body.email})
    if (customer) {
        res.status(200).json(customer)
    }
    else {
        res.status(401).json({message:"Authentication failed"})
    }
});

export default router;