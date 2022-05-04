const mongoose = require('mongoose');

mongoose.connect('mongodb://localhost:27017/CPM_Proj1_DB', {
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