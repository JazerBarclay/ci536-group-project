/** 
 * Register Router
 * Manages request end points for register
 */

// Import modules from register controller
const { validateParams, validateEmail, validateUsername, registerUser } = require('./registerController')

// Add router from express to manage routing
const router = require('express').Router()

// Add post request to /register route. Must pass each module before adding user
router.post('/', validateParams, validateEmail, validateUsername, registerUser)

// Export this router module
module.exports = router;