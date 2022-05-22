const { verify } = require('jsonwebtoken')

module.exports = {
    validateToken: (req, res, next) => {
        // Get token from header
        let token = req.headers.authorization
        
        // Remove 'Bearer ' string before key
        token = token.slice(7)

        // Verify token using jwt
        verify(token, 'secret', (err, decoded) => {

            // If verification fails, return 403
            if (err) return res.status(403).json({ error: "Invalid Token" })

            // Store authorization in request body as auth
            req.body.auth = decoded

            // Go to next
            return next()
        })
    }
}