import "dotenv/config";
import express from "express";
import path from "path";
import cookieParser from "cookie-parser";
import logger from "morgan";
import cors from "cors";
import mongoose from "mongoose";

import routes from "./routes/index.js";

const __dirname = path.resolve();

const dbUri =
    process.env.DB_URL ||
    `mongodb://${process.env.DB_HOST}:${process.env.DB_PORT}/${process.env.DB_NAME}`;

console.log(dbUri);

mongoose
    .connect(dbUri, {
        user: process.env.DB_USER,
        pass: process.env.DB_PASS,
        dbName: process.env.DB_NAME,
        useNewUrlParser: true,
        useUnifiedTopology: true,
        // useCreateIndex: true,
    })
    .then(() => {
        console.log("Mongoose: connection ok!");
    })
    .catch((err) => {
        console.log("Mongoose: failure in initial connection to the DB");
        console.error(err);
    });

const app = express();
const port = process.env.PORT || "3000";

app.listen(port, () => {
    console.log(`Application running in port ${port}.`);
});

app.use(cors());
app.use(logger("dev"));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, "public")));

app.use("/customer", routes.customer)
app.use("/product", routes.product)
app.use("/transaction", routes.transaction)

var db = mongoose.connection

db.once('open', function() {
    console.log("Connection successful!");

    db.collection("products").deleteMany();
    db.collection("products").insertMany([
        {
            "productID": "000000000017",
            "name": "iPhone",
            "price": 1099.99,
            "make": "Apple",
            "model": "13 ",
            "characteristics": "512GB",
        },
        {
            "productID": "000000000024",
            "name": "iMac",
            "price": 1499,
            "make": "Apple",
            "model": "M1",
            "characteristics": "CPU 8‑core GPU 7‑core",
        },
        {
            "productID": "000000000031",
            "name": "Apple Watch",
            "price": 499.99,
            "make": "Apple",
            "model": "Series 7",
            "characteristics": "32GB",
        },
        {
            "productID": "000000000048",
            "name": "Logitech Gaming Mouse",
            "price": 79.99,
            "make": "Logitech",
            "model": "G703 Lightspeed",
            "characteristics": "25600 dpi - wireless",
        },
        {
            "productID": "000000000055",
            "name": "Kraken X Headset",
            "price": 46.99,
            "make": "Razer",
            "model": "Kraken X Lite",
            "characteristics": "7.1 Surround Sound",
        },
        {
            "productID": "000000000062",
            "name": "Asus Zenbook Pro",
            "price": 2099,
            "make": "Asus",
            "model": "UX534",
            "characteristics": "i7-8565U Quad Core, 16GB RAM",
        },
        {
            "productID": "000000000079",
            "name": "Razer Gaming Keyboard",
            "price": 109.99,
            "make": "Razer",
            "model": "Blackwidow V3 Tenkeyless",
            "characteristics": "Hybrid Memory",
        },
        {
            "productID": "000000000086",
            "name": "T300 Wheel",
            "price": 429.9,
            "make": "Thrustmaster",
            "model": "T300 RS GT Edition",
            "characteristics": "Silent Force Feedback",
        },
        {
            "productID": "000000000093",
            "name": "Smart TV LG 4k Ultra HD",
            "price": 1749,
            "make": "LG",
            "model": "OLED55C16LA",
            "characteristics": "OLED - 55'' - 140 cm",
        },
        {
            "productID": "000000000109",
            "name": "PS5",
            "price": 499.99,
            "make": "PlayStation",
            "model": "Physical Edition",
            "characteristics": "825GB",
        }
    ]);
    console.log("Products added!");
})