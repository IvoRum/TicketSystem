const callTicketBtn = document.querySelector('.call-ticket-btn');
const jwtToken = localStorage.getItem('jwtToken');

let headers = new Headers();
headers.append('Authorization', `Bearer ${jwtToken}`);


console.log(jwtToken);
callTicketBtn.addEventListener('click', () => {
  fetch('http://localhost:8080/api/v1/queue/nextInLine/1', {
    method: 'GET',
    headers: headers,
  })  .then(response => response.json())
  .then(data => {
    console.log(data);
  })
  .catch(error => console.error(error));

});
