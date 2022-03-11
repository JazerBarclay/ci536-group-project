window.addEventListener('load', () => {

    graphLink = "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"


    const xValues = ["mon", 'tues', 'weds', 'thurs', 'fri', 'sat', 'sun'];
    var thisWeekData = [1, 0, 2, 0, 0, 3, 4];
    var lastWeekData = [4, 6, 3, 2, 1, 4, 3];


    function getUserProfile() {


    }


    new Chart("graph", {
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