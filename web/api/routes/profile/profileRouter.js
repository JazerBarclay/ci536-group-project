/** 
 * Profile Router
 * Manages request end points for user profile
 */

// Import modules from profile controller
const { getProfile, getProfileByUsername } = require('./profileController')
const { validateToken } = require('../../authentication/tokenValidation')

// Add router from express to manage routing
const router = require('express').Router()

// Add route for getting user profile 
// NOTE: We will need user id added via middleware soon

router.get('/', validateToken, getProfile)
router.get('/:username', validateToken, getProfileByUsername)

// Export this router module
module.exports = router;