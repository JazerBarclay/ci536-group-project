/** 
 * Unit Controller
 * Manages middleware between unit request and response
 */

// Import modules from service here
const { insertUnit } = require('./unitService')

module.exports = {

    addUnit: (req, res) => {

        // res.status(200).json({ id: req.body.auth.id, exp: req.body.auth.exp })

        insertUnit(req.body.auth.id, 'test', '2022-03-25 13:33:15.903333', '2022-03-25 13:33:15.903333', (err, response) => {
            // If database error, return 500 internal error
            if (err) return res.status(500).json({ err })
            return res.status(201).json()
        })
    }

}