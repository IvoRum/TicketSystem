const callTicketBtn = document.querySelector('.call-ticket-btn');
const jwtToken = localStorage.getItem('jwtToken');
let ticketWorking=0;

let headers = new Headers();
headers.append('Authorization', `Bearer ${jwtToken}`);


console.log(jwtToken);
callTicketBtn.addEventListener('click', async (e) =>{
  e.preventDefault();
  const response =
  await fetch('http://localhost:8080/api/v1/queue/nextInLine/1', {
    method: 'GET',
    headers: headers,
  })  .then(response => response.json())
  .then(data => {
    ticketWorking=data.number;
    console.log(data.number);
  })
  .catch(error => console.error(error));
  document.getElementById('ticket-number').innerHTML = ticketWorking;

});
