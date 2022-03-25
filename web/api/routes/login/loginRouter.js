/** 
 * Login Router
 * Manages request end points for login
 */

// Import modules from login controller
const { validateParams, validateEmail, login, issueLoginToken, verifyToken } = require('./loginController')

// Add router from express to manage routing
const router = require('express').Router()

// On login request, validate, try login, then issue login token
// Login takes email and password
// Returns JWT authentication token (contains id and validity duration)
router.post('/', validateParams, validateEmail, login, issueLoginToken)

router.post('/verify', verifyToken)

// Export this router module
module.exports = router;