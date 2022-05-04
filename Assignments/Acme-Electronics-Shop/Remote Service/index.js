require('./models/db')

const express = require('express');
const path = require('path');
const handlebars = require('handlebars');
const exphbs = require('express-handlebars');
const {
    allowInsecurePrototypeAccess
} = require('@handlebars/allow-prototype-access');
const bodyparser = require('body-parser');

const customerController = require('./controllers/customerController');

var app = express();

app.use(bodyparser.urlencoded({extended: false}));
app.use(bodyparser.json());

app.get('/', (req, res) => {
    res.send(`
    <!DOCTYPE html>

    <html>

        <head>
            <title>CPM Proj1 DB</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" 
                integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
            <style>
                #box {
                    background-color: #fff;
                    margin-top: 25px;
                    padding: 20px;
                    -webkit-box-shadow: 10px 10px 20px 1px rgba(0, 0, 0, 0.75);
                    -moz-box-shadow: 10px 10px 20px 1px rgba(0, 0, 0, 0.75);
                    box-shadow: 10px 10px 20px 1px rgba(0, 0, 0, 0.75);
                    border-radius: 10px 10px 10px 10px;
                    -moz-border-radius: 10px 10px 10px 10px;
                    -webkit-border-radius: 10px 10px 10px 10px;
                    border: 0px solid #000000;
                }
            </style>
        </head>

        <body class="bg-info">
            <div id='box' class="col-md-8 offset-md-2">
                <h2>Welcome to CPM Proj1 Database!</h2><br>
                <h3><b> <a href="/customer/list">Customers Database</a></b></h3>
                <h3><b> <a href="/product/list">Products Database</a></b></h3>
                <h3><b> <a href="/transaction/list">Transactions Database</a></b></h3>
            </div>
        </body>

    </html>
    `);
});

app.set('views', path.join(__dirname, '/views/'));

app.engine(
    "hbs",
    exphbs.engine({
        handlebars: allowInsecurePrototypeAccess(handlebars),
        extname: "hbs",
        defaultLayout: 'MainLayout',
        layoutsDir: __dirname + '/views/layouts/',
    })
);

app.set("view engine", "hbs");

app.listen(3000, () => {
    console.log("Express server started at port 3000");
});

app.use('/customer', customerController);