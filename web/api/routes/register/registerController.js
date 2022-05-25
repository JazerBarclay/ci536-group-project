/** 
 * Register Controller
 * Manages middleware between register request and response
 */

// Import modules from user service
const { selectUserByEmail, selectUserByUsername, insertUser } = require('../user/userService')

// Checks if email matches regex pattern ( * @ * [repeat (.*) 2-4 times] )
function validateEmail(elementValue){      
    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,10}$/;
    return emailPattern.test(elementValue);
}

module.exports = {
    
    // Validate user input via post request
    validateParams: (req, res, next) => {
        // If any field is missing or blank return 400 error
        if (!req.body.email || !req.body.username || !req.body.password)
            return res.status(400).json({ error: "missing params" })
        // else call next
        return next()
    },

    // Validate email address meets requirements and doesn't already exist in database
    validateEmail: (req,res,next) => {
        // If email doesn't pass validation, return 400 error
        if (!validateEmail(req.body.email)) return res.status(400).json({ error: "email invalid" })

        // Else check if it exists in the database
        selectUserByEmail(req.body.email, (err, response) => {
            // If database error, return 500 internal error
            if (err) return res.status(500).json({ err })
            // If no results found return 400 error
            if (response.rows.length > 0) return res.status(400).json({ error: "email already in use" })
            // Else call next
            return next()
        })
    },

    // Validate username doesn't already exist in database
    validateUsername: (req,res,next) => {
        selectUserByUsername(req.body.username, (err, response) => {
            if (err) return res.status(500).json({ err })
            if (response.rows.length > 0) return res.status(400).json({ error: "user already exists" })
            return next()
        })
    },

    // Add user to database
    registerUser: (req,res) => {
        insertUser(req.body.email, req.body.username, req.body.password, (err, response) => {
            if (err) return res.status(500).json({ err })
            return res.status(201).json({ status: "success" })
        })
    }

}