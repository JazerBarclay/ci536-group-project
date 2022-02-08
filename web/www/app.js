// Import the .env configuration
require('dotenv').config()

// Add express library 
const express = require('express');

// Create new app variable for handling network requests
const app = express();

// Set the port to either the value from the .env file
// or default it to 3000
const PORT = process.env.PORT || 3000;

// Deploy the files present in the public folder to the live server
app.use(express.static(path.join(__dirname, 'public')));

// Start the app listening and output to the console where it is running
app.listen(PORT, () => console.log(`Server started on port ${PORT}`));