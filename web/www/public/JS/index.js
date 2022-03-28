window.addEventListener("load", () => {


    var logInForm = document.querySelector('#logInForm');
    var signUpButton = document.querySelector('#signUpButton');


    logInForm.addEventListener("submit", function(evt) {

        evt.preventDefault();

        var emailInput = document.querySelector('#emailInput').value;
        var passwordInput = document.querySelector('#passwordInput').value;

        localStorage.clear(); //clearing the login token from storage before signing in again

        signIn(emailInput, passwordInput);



    })


    signUpButton.addEventListener("click", function(evt) { //just a simple redirect to signup page

        window.location.href = "signup.html";

    })



    function signIn(email, password) {

        const url = 'https://dev.api.quark.rocks/login'

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

                    console.log("Invalid Login, Please check username and password or sign up!")

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