// Import the .env configuration
require('dotenv').config()

// Add express library 
const express = require('express');

// Create new app variable for handling network requests
const app = express();

// Set the port to either the value from the .env file
// or default it to 3000
const PORT = process.env.PORT || 3000;

// Set the app handler to use JSON
app.use(express.json())

// Prevent nested objects and use arrays instead
app.use(express.urlencoded({ extended: false }))

// On requests to the root location, respond with 200 OK
// with a JSON response object
app.get('/', (req, res) => {
    res.status(200).json({
        status: 1, message: "api server for timer app"
    })
})

// Start the app listening and output to the console where it is running
app.listen(PORT, () => console.log(`Server started on port ${PORT}`));