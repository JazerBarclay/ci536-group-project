/** 
 * Unit Router
 * Manages request end points for Units
 */

// Import modules from controller here
const { validateToken } = require('../../authentication/tokenValidation')
const { addUnit, getUnit, getUnits } = require('./unitController')


// Add router from express to manage routing
const router = require('express').Router()

// Read from /unit
router.get('/', validateToken, getUnit)

// Insert to /unit
router.post('/', validateToken, addUnit)

// Read all from /unit
router.post('/', validateToken, getUnits)

// Export this router module
module.exports = router;