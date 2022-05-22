/** 
 * Unit Router
 * Manages request end points for Units
 */

// Import modules from controller here
const { validateToken } = require('../../authentication/tokenValidation')
const { addUnit, getUnit, getUnitByUsername } = require('./unitController')


// Add router from express to manage routing
const router = require('express').Router()

// Read from /unit
router.get('/', validateToken, getUnit)

// Read specific user from /unit
router.get('/:username' ,validateToken, getUnitByUsername)

// Insert to /unit
router.post('/', validateToken, addUnit)



// Export this router module
module.exports = router;