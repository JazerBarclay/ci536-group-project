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







})