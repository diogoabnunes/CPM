import express from "express";
const router = express.Router();
import Transaction from "../models/Transaction.js";
import crypto from "crypto";

router.get('/get-all', async (req, res) => {
    try {
        const transactions = await Transaction.find();
        res.status(200).json(transactions);
    }
    catch (error) {
        res.status(400).json({message: error.message});
    }
});

router.get('/get/:transactionID', async (req, res) => {
    const transactions = await Transaction.find({transactionID: req.params.transactionID});
    res.status(200).json({transactions:transactions, message:'Succeeded'});
})

router.post('/add', async (req, res) => {
    const newTransaction = await new Transaction(
        {transactionID: crypto.randomUUID(),
        userID: req.body.userID,
        content: req.body.content,
        date: req.body.date,
        paid: req.body.paid}
    );

    try {
        const data = await newTransaction.save();
        res.status(201).json(data);
    }
    catch (error) {
        res.status(400).json({message: error.message});
    }
});

export default router;