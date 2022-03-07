# API Documentation

## API Structure
| Route | Params | Code | Response | Description |
| :--- | :--: | :--: | :--: | :-- |
| / | none | 200 | Welcome | Standard ok response from server |
| /register | {username,email,pass} | {200,400/406} | {welcome,error} | Registers a new user based on credentials given |
| /login | {email,pass} | {200,401} | {token,error} | Login to api and receive login token for access |
| /user | token | 200 | your account | Show stats page for yourself as a logged in user |
| /user/:username | username | 200 | user | Show stats page for selected user |
| /top100 | none | 200 | top 100 users | Send the current top 100 users on the app |


## How to install
To setup this api server on your local machine or on a live server, follow the appropriate section below.

### Local testing
This is a simple web server built using Express

To run in devmode, run the following command in the console:

```bash
npm run dev
```

This will run the application in development mode where any save will trigger the server to recompile the code locally saving you time.

### Server deployment
To deploy on the server, use this command instead:

```bash
npm run start
```

This will compile and run a single instance of the express server.
