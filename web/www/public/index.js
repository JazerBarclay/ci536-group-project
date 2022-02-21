window.addEventListener("load", () => {


//to sign up button

var signUpButton = document.querySelector('#signUpButton');

    signUpButton.addEventListener("click", function(evt){

        evt.preventDefault();

        window.location.href = "signup.html";
        
    })

var logInButton = document.querySelector('#logInButton');

    logInButton.addEventListener("click", function(evt){


        evt.preventDefault()

        window.location.href = "profile.html";


    })


    //google sign in 

    function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
      }







})