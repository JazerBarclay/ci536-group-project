window.addEventListener("load", () => {
    //to login

    var logInButton = document.querySelector('#logInButton');

    logInButton.addEventListener("click", function(evt) {


    })

    function createAccount(username, email, password) {

        const url = 'http://dev.api.quark.rocks/user'

        var options = {

            method: 'POST',
            headers: { "Content-Type": "application/json" },

            body: {
                username: username,
                email: email,
                password: password
            }

        }


        fetch(url, options)
            .then(response => {

                if (response === 200) {

                    console.log('yippeeee');

                } else {
                    alert(response.status)
                }


            })

    }










})