/** 
 * Login Controller
 * Manages middleware between login request and response
 */

// Import modules from register service
const { verifyLogin } = require('./loginService')
const { selectUserByEmail } = require('../user/userService')

module.exports = {
    
    // Validate user input via post request
    validateParams: (req, res, next) => {
        // If any field is missing or blank return 400 error
        if (!req.body.email || !req.body.password)
            return res.status(400).json({ error: "Missing params" })
        // else call next
        return next()
    },

    validateEmail: (req, res, next) => {
        selectUserByEmail(req.body.email, (err, response) => {
            // If database error, return 500 internal error
            if (err) return res.status(500).json({ err })
            // If no results found return 400 error
            if (response.rows.length < 1) return res.status(400).json({ error: "Incorrect email or password" })
            // Else call next
            return next()
        })
    },

    login: (req, res, next) => {
        verifyLogin(req.body.email, req.body.password, (err, response) => {
            if (err) return res.status(400).json({err})
            if (response.rows.length < 1) return res.status(400).json({ error: "Incorrect email or password" })
            return next()
        })
    },
    
    // Issue token to user
    issueLoginToken: (req, res) => {
        return res.status(200).json({ message: "yay"})
    }

}