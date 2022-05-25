/** 
 * Unit Router
 * Manages request end points for Units
 */

// Import modules from controller here
const { validateToken } = require('../../authentication/tokenValidation')
const { addUnit, getAllUnits, getAllUnitsByUsername, selectThisWeek, selectLastWeek } = require('./unitController')

// Add router from express to manage routing
const router = require('express').Router()

// Read relevant from /unit
router.get('/', validateToken, getAllUnits)

// Insert to /unit
router.post('/', validateToken, addUnit)

router.get('/thisweek', validateToken, selectThisWeek)

router.get('/lastweek', validateToken, selectLastWeek)

// Read specific user from /unit
router.get('/:username' ,validateToken, getAllUnitsByUsername)

// Export this router module
module.exports = router;