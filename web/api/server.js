// Import the .env configuration
require('dotenv').config()

// Import database connection
const db = require('./dbConnection')

// Add express library 
const express = require('express');

// Create new app variable for handling network requests
const app = express();

// Set the port to either the value from the .env file
// or default it to 4000
// User (profile), scores (leaderboard), login(id and password), add session(record the session) 
const PORT = process.env.PORT || 4000;

// Set the app handler to use JSON
app.use(express.json())

// Prevent nested objects and use arrays instead
app.use(express.urlencoded({ extended: false }))

// On requests to the root location, respond with 200 OK
// with a JSON response object
app.get('/', (req, res) => {
    res.status(200).json({
        message: "API Server for Quark App"
    })
})

app.get('/user/:id', (req, res) => {

    db.query(
        `SELECT * FROM users WHERE user_id = $1 LIMIT 1;`,
        [req.params.id],
        (error, results, fields) => {
            if (error) res.status(500).json({error: "Internal Database Error"})
            return res.status(200).json({results: results.rows[0]})
        }
    )

})


app.get('/scores', (req, res) => {
    // On success
    res.status(200).json({
        status: 1, message: "Scores retrieved successfully"
    })
    // On Fail
    // res.status(406).json ({
    //     status: 1, message: "Error! Server error!"
    // })
})

app.get('/profile', (req, res) => {
    res.status(200).json ({
        status: 1, message: "User profile accessed successfully"
    })
    // On Fail
    // res.status(406).json ({
    //    status: 1, message: "Error, could not access the user profile"
    // })


})



// Start the app listening and output to the console where it is running
app.listen(PORT, () => console.log(`Server started on port ${PORT}`));