/** 
 * Login Controller
 * Manages middleware between login request and response
 */


// Import jwt
var jwt = require('jsonwebtoken');

// Import bcrypt for salt + hash
const bcrypt = require ('bcrypt');

// Import modules from register service
const { verifyLogin } = require('./loginService')
const { selectUserByEmail } = require('../user/userService')

module.exports = {
    
    // Validate user input via post request
    validateParams: (req, res, next) => {
        // If any field is missing or blank return 400 error
        if (!req.body.email || !req.body.password)
            return res.status(400).json({ error: "missing params" })
        // else call next
        return next()
    },

    // Validate email exists in database
    validateEmail: (req, res, next) => {
        selectUserByEmail(req.body.email, (err, response) => {
            // If database error, return 500 internal error
            if (err) return res.status(500).json({ err })
            // If no results found return 400 error
            if (response.rows.length < 1) return res.status(400).json({ error: "incorrect email or password" })
            // Else call next
            return next()
        })
    },

    // Check if email and pass match in database
    login: (req, res, next) => {
        verifyLogin(req.body.email, (err, response) => {
            if (err) return res.status(400).json({err})
            if (response.rows.length < 1) return res.status(400).json({ error: "incorrect email or password" })
            bcrypt.compare(req.body.password, response.rows[0].user_password, function(err, result) {
                if (err) return res.status(400).json({err})
                req.id = response.rows[0].user_id
                return next()
            });
        })
    },
    
    // Issue token to user
    issueLoginToken: (req, res) => {
        var token = jwt.sign(
            { id: req.id }, 
            'secret', 
            { expiresIn: '24h' });
        return res.status(200).json({ token })
    },

    // Takes in an encoded token and returns the decoded object
    verifyToken: (req, res) => {
        jwt.verify(req.headers.authorization.slice(7), 'secret', function(err, decoded) {
            if (err) return res.status(400).json({ message: "invalid" })
            return res.status(200).json()
        });
    },

    // Takes in an encoded token and returns the decoded object
    renewToken: (req, res) => {
        jwt.verify(req.headers.authorization.slice(7), 'secret', function(err, decoded) {
            if (err) return res.status(400).json({ message: "invalid" })
            req.id=decoded.id
            var token = jwt.sign(
                { id: req.id }, 
                'secret', 
                { expiresIn: '24h' });
            return res.status(200).json({ token })
        });
    }

}