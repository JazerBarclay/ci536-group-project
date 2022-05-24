/** 
 * Unit Router
 * Manages request end points for Units
 */

// Import modules from controller here
const { validateToken } = require('../../authentication/tokenValidation')
const { addUnit, getAllUnits, getAllUnitsByUsername, selectThisWeekByID, selectLastWeekByID } = require('./unitController')

// Add router from express to manage routing
const router = require('express').Router()

// Read relevant from /unit
router.get('/', validateToken, getAllUnits)

// Insert to /unit
router.post('/', validateToken, addUnit)

// Read specific user from /unit
router.get('/:username' ,validateToken, getAllUnitsByUsername)

// Read relevant from /unit
router.get('/thisweek', validateToken, selectThisWeekByID)

// Read relevant from /unit
router.get('/lastweek', validateToken, selectLastWeekByID)

// Export this router module
module.exports = router;