/** 
 * Profile Controller
 * Manages middleware between profile request and response
 */

// Import modules

const { selectUserByID, selectUserByUsername } = require('../user/userService')


module.exports = {

    // Get user by username
    getProfile: (req, res, next) => {
        selectUserByID(req.body.auth.id, (err, response) => {
            if (err) return res.status(500).json({ err })
            return res.status(200).json(response)
        })
    },

    getProfileByUsername: (req, res, next) => {
        selectUserByUsername(req, (err, response) => {
            if (err) return res.status(500).json({ err })
            return res.status(200).json(response)
        })
    }

    
    
}