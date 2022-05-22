window.addEventListener('load', () => {

    if (localStorage.token == undefined) { //making sure that the user has generated a login token before visiting profile

        console.log("invalid token!")

        window.location.href = "index.html"


    } else {

        console.log("This is a valid session using token: " + localStorage.token); //temporary output used for development


    }

    var graphLink = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"


    const xValues = ["mon", 'tues', 'weds', 'thurs', 'fri', 'sat', 'sun'];

    //The following data will be eventually passed to the graph from the database
    var thisWeekData = [1, 0, 2, 0, 0, 3, 4];
    var lastWeekData = [4, 6, 3, 2, 1, 4, 3];

    //TODO: 
    function getUserProfile() {


    }






    new Chart("graph", { //generating the chart using user input (thisweekdata and lastweekdata)
        type: "line",
        data: {
            labels: xValues,
            datasets: [{
                    label: 'Completed Blocks This Week',
                    data: thisWeekData,
                    borderColor: "black",
                    fill: true
                },
                {

                    label: 'Completed Blocks Last Week',
                    data: lastWeekData,
                    borderColor: "light grey",
                    fill: true
                }
            ]

        },
        options: {
            legend: { display: true }
        }
    });


})