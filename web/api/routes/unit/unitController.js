/** 
 * Unit Controller
 * Manages middleware between unit request and response
 */

// Import modules from service here
const { insertUnit } = require('./unitService')

module.exports = {

    addUnit: (req, res) => {
        insertUnit(req.body.auth.id, req.body.label, req.body.start, req.body.end, (err, response) => {
            // If database error, return 500 internal error
            if (err) return res.status(500).json({ err })
            return res.status(201).json()
        })
    }

}