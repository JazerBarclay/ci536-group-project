const { checkEmail, checkUsername, insertUser } = require('./registerService')

module.exports = {

    validateParams: (req, res, next) => {
        if (!req.body.email || !req.body.username || !req.body.password)
            return res.status(400).json({ error: "Missing params" })
        return next()
    },
    validateEmail: (req,res,next) => {
        checkEmail(req.body.email, (err, response) => {
            if (err) return res.status(500).json({ err })
            if (response.rows.length > 0) return res.status(400).json({ error: "Email already in use" })
            return next()
        })
    },
    validateUsername: (req,res,next) => {
        checkUsername(req.body.username, (err, response) => {
            if (err) return res.status(500).json({ err })
            if (response.rows.length > 0) return res.status(400).json({ error: "User already exists" })
            return next()
        })
    },
    registerUser: (req,res) => {
        insertUser(req.body.email, req.body.username, req.body.password, (err, response) => {
            if (err) return res.status(500).json({ err })
            return res.status(201).json({ status: "Success" })
        })
        
    }

}