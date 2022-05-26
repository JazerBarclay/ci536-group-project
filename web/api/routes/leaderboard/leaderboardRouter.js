/** 
 * Leaderboard Router
 * Manages request end points for leaderboard
 */

// Import modules from login and leaderboard controller
const { validateToken } = require('../../authentication/tokenValidation')
const { leaderboard } = require('./leaderboardController')

// Add router from express to manage routing
const router = require('express').Router()

router.get('/', validateToken, leaderboard)

// Export this router module
module.exports = router;