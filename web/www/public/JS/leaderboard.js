const remoteURL = "https://api.quark.rocks/"
// const remoteURL = "https://dev.api.quark.rocks/"
//const remoteURL = "http://localhost:4000/"

window.addEventListener("load", () => {

    var leaderboard = document.querySelector('#leaderboardTable')

    if (localStorage.token == undefined) {
        console.log("No token!")
        window.location.href = "login.html"
    }
    console.log("This is a valid session using token: " + localStorage.token);

    var url = remoteURL + "leaderboard"

    var myHeaders = new Headers();
    myHeaders.append("Authorization", "Bearer " + localStorage.token);

    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };

    fetch(url, requestOptions)
    .then(response => response.json())
    .then(result => {
        var i = 1;
        result.forEach(e => {
            var tr = document.createElement('tr')
            var td = document.createElement('td')
            td.textContent = i
            tr.appendChild(td)
            td = document.createElement('td')
            td.textContent = e.username
            tr.appendChild(td)
            td = document.createElement('td')
            td.textContent = e.total
            tr.appendChild(td)
            leaderboard.appendChild(tr)
            i++
        });
    })
    .catch(error => console.log('error', error));

})