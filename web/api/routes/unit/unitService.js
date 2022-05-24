/** 
 * Unit Service
 * Manages database services for managing units
 */

// Import database connection
const db = require('../../database/dbConnection')

// Database CREATE, READ, UPDATE, DELETE (CRUD) operations
module.exports = {

    selectAllUnits: (userid, callBack) => {
        db.query(
            `SELECT pomodoro_text, pomodoro_start, pomodoro_end 
            FROM pomodoros 
            WHERE pomodoro_user_id = $1`,
            [userid],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    },

    selectAllUnitsByUsername: (username, callBack) => {
        db.query(`
        SELECT pomodoro_text, pomodoro_start, pomodoro_end 
        FROM pomodoros 
        WHERE pomodoro_user_id = (
            SELECT user_id FROM users WHERE user_username = $1 LIMIT 1
        )`,
        [username],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    },

    selectThisWeekByID: (id, callBack) => {
        db.query(`
            SELECT 
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now() - INTERVAL '6 DAY')::timestamp 
                AND (date_trunc('day', now() - INTERVAL '5 DAY') - INTERVAL '1 SECOND')::timestamp
                then 1 end
            ) as d6,
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now() - INTERVAL '5 DAY')::timestamp 
                AND (date_trunc('day', now() - INTERVAL '4 DAY') - INTERVAL '1 SECOND')::timestamp
                then 1 end
            ) as d5,
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now() - INTERVAL '4 DAY')::timestamp 
                AND (date_trunc('day', now() - INTERVAL '3 DAY') - INTERVAL '1 SECOND')::timestamp
                then 1 end
            ) as d4,
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now() - INTERVAL '3 DAY')::timestamp 
                AND (date_trunc('day', now() - INTERVAL '2 DAY') - INTERVAL '1 SECOND')::timestamp
                then 1 end
            ) as d3,
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now() - INTERVAL '2 DAY')::timestamp 
                AND (date_trunc('day', now() - INTERVAL '1 DAY') - INTERVAL '1 SECOND')::timestamp
                then 1 end
            ) as d2,
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now() - INTERVAL '1 DAY')::timestamp 
                AND (date_trunc('day', now()) - INTERVAL '1 SECOND')::timestamp
                then 1 end
            ) as d1,
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now())::timestamp 
                AND now()::timestamp
                then 1 end
            ) as d0
            FROM (
                SELECT * FROM pomodoros WHERE pomodoro_user_id = $1
            ) pomos;`,
            [id],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    },

    selectLastWeekByID: (id, callBack) => {
        db.query(`
            SELECT 
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now() - INTERVAL '13 DAY')::timestamp 
                AND (date_trunc('day', now() - INTERVAL '12 DAY') - INTERVAL '1 SECOND')::timestamp
                then 1 end
            ) as d6,
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now() - INTERVAL '12 DAY')::timestamp 
                AND (date_trunc('day', now() - INTERVAL '11 DAY') - INTERVAL '1 SECOND')::timestamp
                then 1 end
            ) as d5,
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now() - INTERVAL '11 DAY')::timestamp 
                AND (date_trunc('day', now() - INTERVAL '10 DAY') - INTERVAL '1 SECOND')::timestamp
                then 1 end
            ) as d4,
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now() - INTERVAL '10 DAY')::timestamp 
                AND (date_trunc('day', now() - INTERVAL '9 DAY') - INTERVAL '1 SECOND')::timestamp
                then 1 end
            ) as d3,
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now() - INTERVAL '9 DAY')::timestamp 
                AND (date_trunc('day', now() - INTERVAL '8 DAY') - INTERVAL '1 SECOND')::timestamp
                then 1 end
            ) as d2,
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now() - INTERVAL '8 DAY')::timestamp 
                AND (date_trunc('day', now() - INTERVAL '7 DAY') - INTERVAL '1 SECOND')::timestamp
                then 1 end
            ) as d2,
            COUNT(
                case when pomodoro_start 
                BETWEEN date_trunc('day', now() - INTERVAL '7 DAY')::timestamp 
                AND (date_trunc('day', now() - INTERVAL '6 DAY') - INTERVAL '1 SECOND')::timestamp
                then 1 end
            ) as d0
            FROM (
                SELECT * FROM pomodoros WHERE pomodoro_user_id = $1
            ) pomos;`,
            [id],
            (error, results, fields) => {
                if (error) return callBack(error)
                return callBack(null, results)
            }
        )
    },
    
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