import{document} from'https://cdn.jsdelivr.net/npm/chart.js';

 var ctx = document.getElementById("myChart").getContext("2d");

    // create the chart
    var myChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [{
          label: "My First Dataset",
          data: [10, 20, 30, 40, 50, 60, 70],
          fill: false,
          borderColor: 'rgb(75, 192, 192)',
          tension: 0.1
        }]
      },
      options: {}
    });