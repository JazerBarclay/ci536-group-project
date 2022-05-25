/** 
 * User Router
 * Manages request end points for users
 */

// Import modules from controller here
const { getUser } = require('./userController')
const { validateToken } = require('../../authentication/tokenValidation')


// Add router from express to manage routing
const router = require('express').Router()

// Add route paths here (these are from /user)

// Get your user profile (should redirect to /profile)
// router.get('/', validateInput, divert)

// Get user from /user
router.get('/:username', validateToken, getUser)

// Insert user (should redirect to register route or visa versa)
// router.post('/', validateInput, getUser)

// Update user
// router.patch('/', validateUser, validateInput, updateUser)

// Delete user
// router.delete('/', validateUser, validateID, deleteUser)

// Export this router module
module.exports = router;