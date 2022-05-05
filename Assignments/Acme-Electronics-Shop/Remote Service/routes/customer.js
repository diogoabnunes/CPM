import express from "express";
const router = express.Router();
import Customer from "../models/Customer.js";

router.get('/get-all', async (req, res) => {
    const customers = await Customer.find({});
    res.status(200).json({customers:customers, message:'Succeeded'});
});

router.get('/get/:userID', async (req, res) => {
    const customers = await Customer.find({userID: req.params.userID});
    res.status(200).json({customers:customers, message:'Succeeded'});
})

router.post('/register', async (req, res) => {

    const newCustomer = await new Customer(
        {userID: req.body.userID,
        name: req.body.name,
        address: req.body.address,
        fiscalNumber: req.body.fiscalNumber,
        email: req.body.email,
        password: req.body.password,
        publicKey: req.body.publicKey,
        cardType: req.body.cardType,
        cardNumber: req.body.cardNumber,
        cardValidity: req.body.cardValidity}
    );

    await newCustomer.save();
    res.status(201).json({message:'Succeeded'});
});

export default router;