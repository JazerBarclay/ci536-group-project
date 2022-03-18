const db = require('../../database/dbConnection')

module.exports = {

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