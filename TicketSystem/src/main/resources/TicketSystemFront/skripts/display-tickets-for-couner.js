const ticketsContainer = document.getElementById('ticket-content');

function displayTickets(tickets) {
  tickets.forEach(ticket => {
    const ticketDiv = document.createElement('p');
    ticketDiv.classList.add('ticket-number');
    ticketDiv.innerHTML = `
        ${ticket.id}
    `;
    ticketsContainer.appendChild(ticketDiv);
  });
}

fetch('http://localhost:8080/api/v1/draft/waitingForCounter/1')
  .then(response => response.json())
  .then(data => displayTickets(data))
  .catch(error => console.error(error));
