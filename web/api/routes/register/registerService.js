/** 
 * Register Service
 * Manages database services for registering a new user
 */

// Import database connection
const db = require('../../database/dbConnection')

module.exports = {

    // Search db for email address (checkEmail takes an email string and callback function)
    checkEmail: (email, callBack) => {
        // Search using db query function
        db.query(
            // Select users where email matches param 1 ($1)
            `SELECT * FROM users WHERE user_email = $1;`,
            // Parameters ($1 = email)
            [email],
            // lambda function for error, results and fields
            (error, results, fields) => {
                // If error exists, return the error in callback
                if (error) return callBack(error)
                // Else return results in callback
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