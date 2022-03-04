# CI536 Integrated Group Project - Quark
_A pomodoro time tracker with analytics and leaderboard_

## Requirements

User must be able to sign up for a new account
* Will require username, email and password
* Future plans to use either firebase or other service for alternative signup / login

User confirms email and activates their account
* Code confirmation or link that expires
* Default activation is false

User can then login to their account on either the website or app
* App requires login
* Website requires login
* API will use same credentials

User stats can be recorded via the desktop application
* App will manage recording sessions via a fixed timer
* User can start, pause? and stop the timer
* Timer is 25 min work 5 min rest
* Pomodoro units can be submitted in batches via a work 'session'
* Sessions are made up of pomodoro units
* Sessions are submitted when the timer is stopped
* Maybe in the future we can modify the times with longer work/rest cycles
* This would mean fractional points

User can view these stats on the website
* User logs into the website
* Goes to stats page
* Past work sessions, total units for the day, week, month, year and all time?
* Other views available?

Users (and non users?) can access the leaderboard
* Leaderboard will show top 100 on front page
* User could limit to group / their position globally
* Leaderboard may allow users data to be viewed much like their own account
* Small graph of weekly stats show in list?
* Maybe expandable to give stats on the same page


## Security

Code will only be accessible via github or a developers machine
* Github repository is listed as private to prevent reverse engineering attack
* SSH public key encryption will secure communication with github
* Developer machines will have security on them to prevent loss of these keys
* Keys can be revoked via github in the event of a lost device
* Github 2FA enabled on all developer devices

User's full names, addresses and other personally identifying information must not be recorded
* This is due to our app tracking user's activity
* If a user was correlated to their work activity, bad actors may attempt to utilise this info to their advantage

Users must login with their email
* Usernames will be visible to all via the scoreboard
* To prevent brute force attacks on all accounts, login must be done via email address
* May move to login via email link in the future

User passwords must have at least 6 chars, 1 number and 1 symbol
* To increase security of user passwords, the pass must pass this check

API access must be done using login details
* So that requests can be tracked (frequency, times, etc...) users must use their login credentials to access the API
* Only routes that does not require this is the login route and top 100 scores
* Too frequent requests may result in a timeout

HTTPS used on all communications
* Website and API will have SSL encryption for all communication
* This will be configured via letsencrypt on the nginx server side
* All API requests will be made to this https remote point

## Local development setup
### Segment locations / ports
App - Java application (desktop)

Website - localhost:3000

API - localhost:4000

DB_admin - localhost:5000

Database - localhost:5432

### Dev server endpoints

Website: dev.quark.rocks

API: dev.api.quark.rocks

### Live server endpoints

Website: www.quark.rocks / quark.rocks

API: api.quark.rocks

## Server setup
Live website - localhost:3000

Dev website - localhost:3001

Live API - localhost:4000

Dev API - localhost:4001

Live database - localhost:5432

Dev website - localhost:5433
