/** 
 * Login Service
 * Manages database services for managing login
 */

// Import database connection
const db = require('../../database/dbConnection')

module.exports = {

    // Return all rows where email and password match
    verifyLogin: (email, callBack) => {
        db.query(
            `SELECT * FROM users WHERE user_email = $1;`,
            [email],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    }

}