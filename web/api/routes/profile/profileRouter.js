/** 
 * Profile Router
 * Manages request end points for user profile
 */

// Import modules from profile controller
const { getProfile } = require('./profileController')

// Add router from express to manage routing
const router = require('express').Router()

// Add route for getting user profile 
// NOTE: We will need user id added via middleware soon
router.post('/', getProfile)

// Export this router module
module.exports = router;