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

// Register a new user on the system
app.post('/register', (req, res) => {

    // Received Parameters
    // req.params.email, req.params.username, req.params.password

    // Validate email, username and password meet minimum requirements

    // Check username or email exists in db
    // db.query('select', (error, result, fields) => { ... })

    // If db returns > 0 then response.json failed due to existing user / email

    // Add new user (salt + hash password)
    // db.query('insert', (error, result, fields) => { ... })

    // JSON response with insert successful

})

// Authenticate existing user to access the api and website
app.post('/login', (req, res) => {

    // Received Parameters
    // req.params.email, req.params.password

    // Check email is valid and passwords match (salt + hash to compare)
    // db.query('select', (error, result, fields) => { ... })

    // If details match, create token and send in response

})

// Return the profile matching the user token
app.get('/profile', (req, res) => {

    // Search db matching user id and token
    // db.query('select', (error, result, fields) => { ... })

    // Example response
    res.status(200).json({
        username: "username",
        email: "email@address.xyz",
        units: [{
                start: "10:00:00",
                end: "10:25:00"
            },
            {
                start: "10:30:00",
                end: "10:55:00"
            }
        ]
    })

    // Example on-fail response
    // res.status(406).json ({
    //    status: 1, message: "Error, could not access the user profile"
    // })


})

// Return the profile of a requested user by username
app.get('/user/:username', (req, res) => {

    // Query db
    db.query(
        // Query term
        `SELECT * FROM users WHERE user_username = $1;`,
        // Parameters (referred to in query by $1,$2...)
        [req.params.username],
        // Callback function that returns either error, results with fields
        (error, results, fields) => {
            // If error exists, return 500 error code
            if (error) return res.status(500).json({ error: "Internal Database Error" })
                // If no results found, return 404 error code
            if (results.rows.length < 1) return res.status(404).json({ message: "User not found" })
                // Since all checks pass, return requested object with 200 code
            return res.status(200).json({
                // Send username
                username: results.rows[0].user_username,
                // Send email
                email: results.rows[0].user_email,
            })
        }
    )

})


app.get('/scores', (req, res) => {
    // On success
    res.status(200).json({
            status: 1,
            message: "Scores retrieved successfully"
        })
        // On Fail
        // res.status(406).json ({
        //     status: 1, message: "Error! Server error!"
        // })
})



// Start the app listening and output to the console where it is running
app.listen(PORT, () => console.log(`Server started on port ${PORT}`));