/** 
 * Login Controller
 * Manages middleware between login request and response
 */

// Import modules from register service
const { getLeaderboard } = require('./leaderboardService')

module.exports = {
    
    // Validate user input via post request
    leaderboard: (req, res) => {
        getLeaderboard((err, response) => {
            if (err) return res.status(400).json({err})
            return res.status(200).json(response.rows)
        })
    }

}