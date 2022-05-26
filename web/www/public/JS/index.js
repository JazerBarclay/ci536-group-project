window.addEventListener('load', () =>{

    var signUpButton = document.querySelector('#sign-up');

    signUpButton.addEventListener('click', evt =>{
        window.location.href = "signup.html"
    })

    var login = document.querySelector('#navLogin')

    if (window.localStorage.token){
        login.textContent = "Profile"
        login.href = "profile.html"
    }


})