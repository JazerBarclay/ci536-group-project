window.addEventListener("load", () => {


    //to sign up button


    var logInForm = document.querySelector('#logInForm');


    logInForm.addEventListener("submit", function(evt) {

        evt.preventDefault();
        console.log("submitted")

        var emailInput = document.querySelector('#emailInput').value;
        var passwordInput = document.querySelector('#passwordInput').value;

        signIn(emailInput, passwordInput);

        //window.location.href = "profile.html"



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
        .then(result => 

                console.log("User Authenticated, Token: " + result.token)

            )

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