window.addEventListener("load", () => {


//to sign up button

var signUpButton = document.querySelector('#signUpButton');

    signUpButton.addEventListener("click", function(evt){

        evt.preventDefault();

        window.location.href = "signup.html";
        
    })

var logInButton = document.querySelector('#logInButton');


    logInButton.addEventListener("click", function(evt){

        evt.preventDefault();

        var emailInput = document.querySelector('.email').value;
        var passwordInput = document.querySelector('.password').value;

        signIn(emailInput, passwordInput);
        


    })



    function signIn(email, password){

        const url = 'https://api.quark.rocks/profile'

        options = {

            method: 'POST',
            mode: 'cors',
            headers: {"Content-Type": "application/json"},
            body: {
                email: email,
                password: password
            }

        }

        fetch(url, options)
        .then(response => {

            if(response === 200){

                console.log('yippeeee');

            }else{
                alert(response.status)
            }


        })

    }

    //google sign in 

    function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
      }







})