import express from "express";
const router = express.Router();
import Product from "../models/Product.js";

router.get('/get-all', async (req, res) => {
    try {
        const products = await Product.find();
        res.status(200).json(products);
    }
    catch (error) {
        res.status(400).message({message: error.message});
    }
});

router.get('/get/:productID', async (req, res) => {
    const products = await Product.find({productID: req.params.productID});
    res.status(200).json(products);
});

router.post('/add', async (req, res) => {

    const newProduct = await new Product(
        {productID: req.body.productID,
        name: req.body.name,
        price: req.body.price,
        make: req.body.make,
        model: req.body.model,
        characteristics: req.body.characteristics}
    );

    try {
        const data = await newProduct.save();
        res.status(201).json(data);
    }
    catch (error) {
        res.status(400).json({message: error.message})
    }
});

export default router;