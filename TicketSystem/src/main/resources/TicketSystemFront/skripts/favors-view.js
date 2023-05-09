const dataListElement = document.getElementById('favors');
const callTicketBtn = document.querySelector('.favors');

const now = new Date();
let nextTicketID = 0;
const hours = now.getHours().toString().padStart(2, '0');
const minutes = now.getMinutes().toString().padStart(2, '0');
const seconds = now.getSeconds().toString().padStart(2, '0');

const time = `${hours}:${minutes}:${seconds}`;
console.log(time);


function displayFavors(favors) {
    favors.forEach(favor => {
        const favorDiv = document.createElement('button');
        favorDiv.className = 'call-ticket-btn';
        favorDiv.id = favor.name;
        favorDiv.type = 'button';
        favorDiv.innerHTML = `${favor.name}`;

        dataListElement.appendChild(favorDiv);
    });
}

fetch('http://localhost:8080/api/v1/draft/favor')
    .then(response => response.json())
    .then(data => displayFavors(data))
    .catch(error => console.error(error));

callTicketBtn.addEventListener('click', async (e) => {
    e.preventDefault();


    const response =
        await fetch('http://localhost:8080/api/v1/draft/lastPersonalTicket')
            .then(response => response.json())
            .then(ptData => {
                nextTicketID = ptData + 1;
                console.log(ptData);
            })
            .catch(error => console.error(error));
    console.log(nextTicketID);
    const responseDraft =
        await fetch('http://localhost:8080/api/v1/draft/1', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                id: nextTicketID,
                issueTime: time,
            }),
        });

    localStorage.setItem('personal-ticket-id', nextTicketID);
    location.href = 'http://127.0.0.1:5173/drafted-ticket-view.html';
});
