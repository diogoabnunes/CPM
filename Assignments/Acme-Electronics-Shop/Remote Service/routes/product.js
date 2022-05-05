import express from "express";
const router = express.Router();
import Product from "../models/Product.js";

router.get('/get-all', async (req, res) => {
    const products = await Product.find({});
    res.status(200).json({products:products, message:'Succeeded'});
});

router.get('/get/:_id', async (req, res) => {
    const products = await Product.find({_id: req.params._id});
    res.status(200).json({products:products, message:'Succeeded'});
});

router.post('/add', async (req, res) => {

    const newProduct = await new Product(
        {name: req.body.name,
        price: req.body.price,
        make: req.body.make,
        model: req.body.model,
        characteristics: req.body.characteristics}
    );

    await newProduct.save();
    res.status(201).json({message:'Succeeded'});
});

export default router;