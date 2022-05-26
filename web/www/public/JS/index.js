window.addEventListener('load', () =>{

    var signUpButton = document.querySelector('#sign-up');
    var downloadButton = document.querySelector('#downloadButton');

    signUpButton.addEventListener('click', evt =>{
        window.location.href = "signup.html"
    })

    downloadButton.addEventListener('click', evt =>{
        window.location.href = "download.html"
    })

    var login = document.querySelector('#navLogin')

    if (window.localStorage.token){
        login.textContent = "Profile"
        login.href = "profile.html"
    }


})