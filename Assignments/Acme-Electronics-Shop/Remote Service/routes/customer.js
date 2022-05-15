import express from "express";
const router = express.Router();
import Customer from "../models/Customer.js";
import bcrypt from "bcrypt";
import crypto from "crypto";
import jwt from "jsonwebtoken";

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
    await Customer.find({email: req.body.email})
        .exec()
        .then(user => {
            if (user.length < 1) {
                return res.status(401).json({message:"Authentication failed..."});
            }
            bcrypt.compare(req.body.password, user[0].password, (err, result) => {
                if (err) {
                    return res.status(401).json({message:"Authentication failed..."});
                }
                if (result) {
                    const token = jwt.sign({
                        email: user[0].email,
                        userID: user[0].userID
                    },
                    'secret',
                    {
                        expiresIn: "1h"
                    });
                    return res.status(200).json({
                        message:"Authentication successful!",
                        token: token
                    });
                }
                return res.status(401).json({message:"Authentication failed..."});
            });
        })
        .catch(err => {
            console.log(err);
            res.status(500).json({error:err});
        });
});

export default router;