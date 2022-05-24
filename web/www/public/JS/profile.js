window.addEventListener('load', () => {

    if (localStorage.token == undefined) { //making sure that the user has generated a login token before visiting profile
        console.log("No token!")
        window.location.href = "login.html"
    }

    console.log("This is a valid session using token: " + localStorage.token); //temporary output used for development
    var userAuth = localStorage.token;

    //set username and email display 
    var usernameDisplay = document.querySelector('#userNameLabel');
    var emailDisplay = document.querySelector('#userEmailLabel');

    //get user details and units here

    setUserDetails(userAuth,usernameDisplay,emailDisplay);
    getUserUnits(userAuth);

    







    var graphLink = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"

    const xValues = ["Mon", 'Tues', 'Weds', 'Thurs', 'Fri', 'Sat', 'Sun'];

    //The following data will be eventually passed to the graph from the database
    var thisWeekData = [1, 0, 2, 0, 0, 3, 4];
    var lastWeekData = [4, 6, 3, 2, 1, 4, 3];

    new Chart("graph", { //generating the chart using user input (thisweekdata and lastweekdata)
        type: "line",
        data: {
            labels: xValues,
            datasets: [{
                    label: 'Completed Blocks This Week',
                    data: thisWeekData,
                    borderColor: "white",
                    fill: true
                }, {
                    label: 'Completed Blocks Last Week',
                    data: lastWeekData,
                    borderColor: "#777",
                    fill: false
                }
            ]

        },
        options: {
            legend: { display: true }
        }
    });






})


function setUserDetails(token,usernameDisplay,emailDisplay){

    var myHeaders = new Headers();
    myHeaders.append("Authorization", "Bearer " + token);

    var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow'
    };

    fetch("https://dev.api.quark.rocks/profile", requestOptions)
    .then(response => response.json())
    .then(result => {

        var user_email = result.rows[0].user_email;
        var user_username = result.rows[0].user_username;
        usernameDisplay.textContent = user_username;
        emailDisplay.textContent = user_email;

    })
    .catch(error => console.log('error', error));

}

function getUserUnits(token) {
    

    var myHeaders = new Headers();
    myHeaders.append("Authorization", "Bearer " + token);
    
    var requestOptions = {
      method: 'GET',
      headers: myHeaders,
      redirect: 'follow'
    };
    
    fetch("https://dev.api.quark.rocks/unit/thisweek", requestOptions)
      .then(response => response.json())
      .then(result => console.log(result))
      .catch(error => console.log('error', error));



}

