/** 
 * Unit Controller
 * Manages middleware between unit request and response
 */

// Import modules from service here
const { selectAllUnits, insertUnit, selectAllUnitsByUsername, selectThisWeekByID, selectLastWeekByID } = require('./unitService')

module.exports = {
    
    getAllUnits: (req, res) => {
        selectAllUnits(req.body.auth.id, (err, response) => {
            // If database error, return 500 internal error
            if (err) return res.status(500).json({ err })
            
            return res.status(200).json(response.rows)
        })
    },

    addUnit: (req, res) => {
        insertUnit(req.body.auth.id, req.body.label, req.body.start, req.body.end, (err, response) => {
            // If database error, return 500 internal error
            if (err) return res.status(500).json({ err })
            return res.status(201).json()
        })
    },

    getAllUnitsByUsername: (req, res) => {
        selectAllUnitsByUsername(req.params.username, (err, response) =>{
            if (err) return res.status(500).json({ err })
            return res.status(200).json(response.rows)
        })
    },

    selectThisWeek: (req, res) => {
        selectThisWeekByID(req.body.auth.id, (err, response) =>{
            if (err) return res.status(500).json({ err })
            return res.status(200).json({ data: [response.rows[0].d6,response.rows[0].d5,response.rows[0].d4,response.rows[0].d3,response.rows[0].d2,response.rows[0].d1,response.rows[0].d0] })
        })
    },

    selectLastWeek: (req, res) => {
        selectLastWeekByID(req.body.auth.id, (err, response) =>{
            if (err) return res.status(500).json({ err })
            console.log(response.rows[0].d0)
            return res.status(200).json({ data: [response.rows[0].d6,response.rows[0].d5,response.rows[0].d4,response.rows[0].d3,response.rows[0].d2,response.rows[0].d1,response.rows[0].d0] })
        })
    }

}