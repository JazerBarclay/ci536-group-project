/** 
 * Leaderboard Service
 * Manages database services for managing leaderboard
 */

// Import database connection
const db = require('../../database/dbConnection')

module.exports = {

    // Return all leaderboar results
    getLeaderboard: (callBack) => {
        db.query(
            `SELECT * FROM leaderboard LIMIT 100;`,
            [],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    }

}