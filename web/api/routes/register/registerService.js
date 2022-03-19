/** 
 * Register Service
 * Manages database services for registering a new user
 */

// Import database connection
const db = require('../../database/dbConnection')

module.exports = {

    // Search db for email address
    checkEmail: (email, callBack) => {
        db.query(
            `SELECT * FROM users WHERE user_email = $1;`,
            [email],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    },

    // Search db for username
    checkUsername: (username, callBack) => {
        db.query(
            `SELECT * FROM users WHERE user_username = $1;`,
            [username],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    },

    // Insert new user into database
    insertUser: (email, username, password, callBack) => {
        db.query(
            `INSERT INTO users (
                user_email, user_username, user_password
            ) VALUES ($1,$2,$3) RETURNING *;`,
            [email, username, password],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    }

}