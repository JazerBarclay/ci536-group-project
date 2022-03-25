/** 
 * Unit Service
 * Manages database services for managing units
 */

// Import database connection
const db = require('../../database/dbConnection')

// Database CREATE, READ, UPDATE, DELETE (CRUD) operations
module.exports = {
    
    insertUnit: (userid, label, start, end, callBack) => {
        db.query(
            `INSERT INTO pomodoros (
                pomodoro_user_id, pomodoro_text, pomodoro_start, pomodoro_end
            ) VALUES ($1,$2,$3,$4) RETURNING *;`,
            [userid, label, start, end],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    }

}