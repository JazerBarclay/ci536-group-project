const { Pool } = require('pg')

console.log(`Connected to ${process.env.DATABASE_URL}`)

const databaseConfig = { connectionString: process.env.DATABASE_URL }
const pool = new Pool(databaseConfig)

module.exports = pool