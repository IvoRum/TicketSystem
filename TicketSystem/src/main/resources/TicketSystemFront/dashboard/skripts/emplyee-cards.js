const dataListElement = document.getElementById('user-column');
const jwtToken = localStorage.getItem('jwtToken');
let ticketWorking = 0;

let headers = new Headers();
headers.append('Authorization', `Bearer ${jwtToken}`);

function displayFavors(favors) {
    favors.forEach(favor => {
        const favorDiv = document.createElement('div');
        favorDiv.className = 'userCard';
        favorDiv.id = favor.name;
        favorDiv.innerHTML = `Id: ${favor.id} Name: ${favor.firstname} Email: ${favor.email} `;

        dataListElement.appendChild(favorDiv);
    });
}

const userResponse =
    fetch('http://localhost:8080/api/v1/user', {
        method: 'GET',
        headers: headers,
    }).then(userResponse => userResponse.json())
        .then(data => displayFavors(data))
        .catch(error => console.error(error));
