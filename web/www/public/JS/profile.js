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
    var unitDisplay = document.querySelector('#userTotalCompletedUnits');
    var totalUnits = 0;

    // var graphLink = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"

    const xValues = ["Mon", 'Tues', 'Weds', 'Thurs', 'Fri', 'Sat', 'Sun'];

    chart = new Chart("graph", { //generating the chart using user input (thisweekdata and lastweekdata)
        type: "line",
        data: {
            labels: xValues,
            datasets: [{
                    label: 'Completed Blocks This Week',
                    data: [],
                    borderColor: "white",
                    fill: true
                }, {
                    label: 'Completed Blocks Last Week',
                    data: [],
                    borderColor: "#777",
                    fill: false
                }
            ]

        },
        options: {
            legend: { display: true }
        }
    });

    //get user details and units here

    setUserDetails(userAuth,usernameDisplay,emailDisplay);
    setUserUnitsThisWeek(userAuth);
    setUserUnitsLastWeek(userAuth);
    setUserUnitsAllTime(userAuth,totalUnits,unitDisplay);
    

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

function setUserUnitsThisWeek(token) {
    
    var myHeaders = new Headers();
    myHeaders.append("Authorization", "Bearer " + token);
    
    var requestOptions = {
      method: 'GET',
      headers: myHeaders,
      redirect: 'follow'
    };
    
    fetch("https://dev.api.quark.rocks/unit/thisweek", requestOptions)
      .then(response => response.json())
      .then(result => {
        chart.data.datasets[0].data = result.data
        chart.update()
    })
    .catch(error => console.log('error', error));

}

function setUserUnitsLastWeek(token) {
    
    var myHeaders = new Headers();
    myHeaders.append("Authorization", "Bearer " + token);
    
    var requestOptions = {
      method: 'GET',
      headers: myHeaders,
      redirect: 'follow'
    };
    
    fetch("https://dev.api.quark.rocks/unit/lastweek", requestOptions)
      .then(response => response.json())
      .then(result => {
        chart.data.datasets[1].data = result.data
        chart.update()
    })
    .catch(error => console.log('error', error));

}

function setUserUnitsAllTime(token,totalUnits,unitDisplay) {
    
    var myHeaders = new Headers();
    myHeaders.append("Authorization", "Bearer " + token);
    
    var requestOptions = {
      method: 'GET',
      headers: myHeaders,
      redirect: 'follow'
    };
    
    fetch("https://dev.api.quark.rocks/unit", requestOptions)
      .then(response => response.json())
      .then(result => {

        totalUnits = result.length
        unitDisplay.textContent = totalUnits;
        
    })
    .catch(error => console.log('error', error));

}
