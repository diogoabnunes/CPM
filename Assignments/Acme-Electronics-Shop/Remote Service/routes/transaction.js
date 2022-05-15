import express from "express";
const router = express.Router();
import Transaction from "../models/Transaction.js";
import crypto from "crypto";

router.get('/get-all', async (req, res) => {
    const transactions = await Transaction.find({});
    res.status(200).json({transactions:transactions, message:'Succeeded'});
});

router.get('/get/:userID', async (req, res) => {
    const transactions = await Transaction.find({userID: req.body.userID});
    res.status(200).json({transactions:transactions, message:'Succeeded'});
})

router.post('/add', async (req, res) => {
    const newTransaction = await new Transaction(
        {transactionID: crypto.randomUUID(),
        userID: req.body.userID,
        content: req.body.content,
        date: req.body.date,
        printed: req.body.printed}
    );

    await newTransaction.save();
    res.status(201).json({message:'Transaction Registered'});
});

export default router;