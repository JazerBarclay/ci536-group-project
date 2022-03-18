const { validateParams, validateEmail, validateUsername, registerUser } = require('./registerController')
const router = require('express').Router()

router.post('/', validateParams, validateEmail, validateUsername,  registerUser)

module.exports = router;