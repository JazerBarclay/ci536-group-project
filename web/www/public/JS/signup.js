window.addEventListener("load", () => {
    //to login

    signUpForm = document.querySelector('#signUpForm')

    signUpForm.addEventListener("submit", function(evt) {

        evt.preventDefault();

        username = document.querySelector(".username").value;
        email = document.querySelector(".email").value;
        password = document.querySelector(".password").value;

        createAccount(username, email, password);



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
            .then(result => console.log(result))
            .catch(error => console.log('error', error));

    }










})