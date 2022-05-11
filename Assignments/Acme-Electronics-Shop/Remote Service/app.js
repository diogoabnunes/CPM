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