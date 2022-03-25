window.addEventListener("load", () => {
    //to login

    signUpForm = document.querySelector('#signUpForm')
    toLogInButton = document.querySelector('#toLogInButton')

    signUpForm.addEventListener("submit", function(evt) {

        evt.preventDefault();

        username = document.querySelector(".username").value;
        email = document.querySelector(".email").value;
        password = document.querySelector(".password").value;

        createAccount(username, email, password);


    })

    toLogInButton.addEventListener("click", function(evt){

        window.location.href = "index.html";

    })

    function createAccount(username, email, password) {

        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        var raw = JSON.stringify({
            "username": username,
            "email": email,
            "password": password
        });

        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: raw,
            redirect: 'follow'
        };

        fetch("https://dev.api.quark.rocks/register", requestOptions)
            .then(response => response.text())
            .then(result => {
                
                console.log(result);
                window.location.href = "index.html";

            })
            .catch(error => console.log('error', error));

    }










})