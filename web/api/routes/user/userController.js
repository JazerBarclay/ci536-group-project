/** 
 * User Controller
 * Manages middleware between user request and response
 */

// Import modules from service here
const { selectUserByEmail, insertUser } = require('./userService')

// Checks if email matches regex pattern ( * @ * [repeat (.*) 2-4 times] )
function validateEmail(elementValue){      
    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,10}$/;
    return emailPattern.test(elementValue);
}

module.exports = {
    
    // Validate email address meets requirements and doesn't already exist in database
    validateEmail: (req,res,next) => {
        // If email doesn't pass validation, return 400 error
        if (!validateEmail(req.body.email)) return res.status(400).json({ error: "Email invalid" })

        // Else check if it exists in the database
        selectUserByEmail(req.body.email, (err, response) => {
            // If database error, return 500 internal error
            if (err) return res.status(500).json({ err })
            // If no results found return 400 error
            if (response.rows.length > 0) return res.status(400).json({ error: "Email already in use" })
            // Else call next
            return next()
        })
    },

    // Validate username doesn't already exist in database
    validateUsername: (req,res,next) => {
        checkUsername(req.body.username, (err, response) => {
            if (err) return res.status(500).json({ err })
            if (response.rows.length > 0) return res.status(400).json({ error: "User already exists" })
            return next()
        })
    },

    addUser: (req, res) => {
        insertUser(req.body.email, req.body.username, req.body.password, (err, response) => {
            if (err) return res.status(500).json({ err })
            return res.status(201).json({ status: "Success" })
        })
    },

}