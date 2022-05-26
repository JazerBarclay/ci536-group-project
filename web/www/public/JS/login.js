// const remoteURL = "https://api.quark.rocks/"
const remoteURL = "https://dev.api.quark.rocks/"
//const remoteURL = "http://localhost:4000/"

window.addEventListener("load", () => {

    localStorage.clear();

    var logInForm = document.querySelector('#loginForm');
    var signUpButton = document.querySelector('#signUpButton');
    var errorDisplay = document.querySelector('#errorDisplay');

    if (window.localStorage.token){
        window.location.href = "profile.html"
    }


    logInForm.addEventListener("submit", function(evt) {

        evt.preventDefault();

        var emailInput = document.querySelector('#emailInput').value;
        var passwordInput = document.querySelector('#passwordInput').value;

        localStorage.clear(); //clearing the login token from storage before signing in again

        signIn(emailInput, passwordInput);

    })

    function signIn(email, password) {

        const url = remoteURL + 'login'

        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        var raw = JSON.stringify({
            "email": email,
            "password": password
        });

        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: raw,
            redirect: 'follow'
        };

        fetch(url, requestOptions)
            .then(response => response.json())
            .then(result => {

                if (result.token != undefined) {

                    console.log("User Authenticated, Token: " + result.token)

                    window.localStorage.setItem("token", result.token)

                    window.location.href = "profile.html"

                } else {

                    errorDisplay.style.display = "inline";
                    errorDisplay.textContent = "Invalid Login, Please check username and password or sign up!";

                }
            })

        .catch(error => console.log('error', error));

    }

    //google sign in 

    // function onSignIn(googleUser) {
    //     var profile = googleUser.getBasicProfile();
    //     console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    //     console.log('Name: ' + profile.getName());
    //     console.log('Image URL: ' + profile.getImageUrl());
    //     console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
    // }







})