// const remoteURL = "https://api.quark.rocks/"
const remoteURL = "https://dev.api.quark.rocks/"
// const remoteURL = "http://localhost:4000/"

window.addEventListener("load", () => {

    signUpForm = document.querySelector('#signupForm')

    signUpForm.addEventListener("submit", function(evt) {
        evt.preventDefault();

        username = document.querySelector("#usernameInput").value;
        email = document.querySelector("#emailInput").value;
        password = document.querySelector("#passwordInput").value;

        createAccount(username, email, password);
    })

})

function createAccount(username, email, password) {

    var url = remoteURL + "register";

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

    fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
            console.log(result);
            window.location.href = "index.html";
        })
        .catch(error => console.log('error', error));

}