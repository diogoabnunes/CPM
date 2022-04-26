const mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/CustomerDB', {
    useNewUrlParser: true,
},
err => {
    if (!err) {
        console.log('Connection succeeded');
    } else {
        console.log('Error in connection ' + err);
    }
})

require('./customer.model');