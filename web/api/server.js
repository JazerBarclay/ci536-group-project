// Import the .env configuration
require('dotenv').config()

// Import database connection (Will be removed in the future when all routes complete)
const db = require('./database/dbConnection')

// Add express library
const express = require('express');

// Add cross origin resource security
const cors = require('cors');

// Create new app variable for handling network requests
const app = express();

// Allow all via cors
// TODO: remove on production
app.use(cors({
    origin: '*'
}));

// Import routes
const registerRouter = require('./routes/register/registerRouter')
const loginRouter = require('./routes/login/loginRouter')
const userRouter = require('./routes/user/userRouter')
const unitRouter = require('./routes/unit/unitRouter')
const profileRouter = require('./routes/profile/profileRouter')

// Set the port to either the value from the .env file
// or default it to 4000
// User (profile), scores (leaderboard), login(id and password), add session(record the session)
const PORT = process.env.PORT || 4000;

// Set the app handler to use JSON
app.use(express.json())

// Prevent nested objects and use arrays instead
app.use(express.urlencoded({ extended: false }))

// Add api routes
app.use('/register', registerRouter)
app.use('/login', loginRouter)
app.use('/user', userRouter)
app.use('/unit', unitRouter)
app.use('/profile', profileRouter)

// TODO: Add profile route
// app.use('/profile', profileRouter)


// On requests to the root location, respond with 200 OK
// with a JSON response object
app.get('/', (req, res) => {
    res.status(200).json({
        message: "API Server for Quark App"
    })
})

// Start the app listening and output to the console where it is running
app.listen(PORT, () => console.log(`Server started on port ${PORT}`));