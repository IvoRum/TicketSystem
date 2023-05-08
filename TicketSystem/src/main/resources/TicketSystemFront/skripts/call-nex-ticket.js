const ticketNumberEl = document.querySelector('.ticket-number');
const callTicketBtn = document.querySelector('.call-ticket-btn');
const jwtToken = localStorage.getItem('jwtToken');

callTicketBtn.addEventListener('click', () => {
  fetch('http://localhost:8080/api/v1/queue/nextInLine/1', {
    headers: {
      'Authorization': `Bearer ${jwtToken}`
    }
  })  .then(response => response.json())
  .then(data => {
    // do something with the data
  })
  .catch(error => console.error(error));

});
