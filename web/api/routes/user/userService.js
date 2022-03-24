/** 
 * User Service
 * Manages database services for managing users
 */

// Import database connection
const db = require('../../database/dbConnection')

// Database CREATE, READ, UPDATE, DELETE (CRUD) operations
module.exports = {

    // Insert New User
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
    },

    // Select all users
    selectAllUsers: (callBack) => {
        db.query(
            `SELECT * FROM users;`,
            [],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    },

    // Select user by user id
    selectUserByID: (id, callBack) => {
        db.query(
            `SELECT * FROM users WHERE user_id = $1;`,
            [id],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    },

    // Select user by username
    selectUserByUsername: (username, callBack) => {
        db.query(
            `SELECT * FROM users WHERE user_username = $1;`,
            [username],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    },

    // Select user by email address
    selectUserByEmail: (email, callBack) => {
        db.query(
            `SELECT * FROM users WHERE user_email = $1;`,
            [email],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    },

    // Update User
    updateUser: (id, username, email, password, callBack) => {
        db.query(
            `UPDATE users 
            SET user_username = $2, user_email = $3, user_password = $4
            WHERE user_id = $1 RETURNING *;`,
            [id, username, email, password],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    },

    // Delete user
    deleteUser: (id, callBack) => {
        db.query(
            `DELETE FROM users WHERE user_id = $1;`,
            [id],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    }

}