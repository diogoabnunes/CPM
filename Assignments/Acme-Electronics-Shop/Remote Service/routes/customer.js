import express from "express";
const router = express.Router();
import Customer from "../models/Customer.js";
import bcrypt from "bcrypt";
import crypto from "crypto";

router.get('/get-all', async (req, res) => {
    try {
        const customers = await Customer.find({});
        res.json(customers);
    }
    catch (error) {
        res.status(400).json({message: error.message});
    }
});

router.get('/get/:userID', async (req, res) => {
    const customers = await Customer.find({userID: req.params.userID});
    res.status(200).json({customers:customers, message:'Succeeded'});
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
        cardValidity: req.body.cardValidity}
    );

    try {
        const data = await newCustomer.save();
        res.status(200).json(data);
    }
    catch (error) {
        res.status(400).json({message: error.message})
    }
});

router.post('/login', async (req, res) => {
    const customer = await Customer.findOne({email: req.body.email});
    if (customer) {
        const cmp = await bcrypt.compare(req.body.password, customer.password);
    
        if (cmp) {
            res.status(200).json({message:"Customer Logged In"});
        }
        else {
            res.status(401).json({message:"Wrong Password..."});
        }
    }
    else {
        res.status(401).json({message:"Wrong Email..."});
    }
})

export default router;