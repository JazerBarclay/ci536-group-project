/** 
 * Register Controller
 * Manages middleware between register request and response
 */

// Import modules from register service
const { checkEmail, checkUsername, insertUser } = require('./registerService')

// Checks if email matches regex pattern ( * @ * [repeat (.*) 2-4 times] )
function validateEmail(elementValue){      
    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,10}$/;
    return emailPattern.test(elementValue);
}

module.exports = {
    
    // Validate user input via post request
    validateParams: (req, res, next) => {
        if (!req.body.email || !req.body.username || !req.body.password)
            return res.status(400).json({ error: "Missing params" })
        return next()
    },

    // Validate email address meets requirements and doesn't already exist in database
    validateEmail: (req,res,next) => {
        
        if (!validateEmail(req.body.email)) return res.status(400).json({ error: "Email invalid" })

        checkEmail(req.body.email, (err, response) => {
            if (err) return res.status(500).json({ err })
            if (response.rows.length > 0) return res.status(400).json({ error: "Email already in use" })
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

    // Add user to database
    registerUser: (req,res) => {
        insertUser(req.body.email, req.body.username, req.body.password, (err, response) => {
            if (err) return res.status(500).json({ err })
            return res.status(201).json({ status: "Success" })
        })
    }

}