const dataListElement = document.getElementById('favors');

function displayFavors(favors) {
    favors.forEach(favor => {
        console.log('Data list:', favor);
        const favorDiv = document.createElement('button');
        //favorDiv.classList.data('favor-name');
        favorDiv.innerHTML = `${favor.name}`;
        dataListElement.appendChild(favorDiv);
    });
}

fetch('http://localhost:8080/api/v1/draft/favor')
    .then(response => response.json())
    .then(data => displayFavors(data))
    .catch(error => console.error(error));