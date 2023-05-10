const form = document.querySelector('#favor-form');
const jwtToken = localStorage.getItem('jwtToken');


let headers = new Headers();
headers.append('Authorization', `Bearer ${jwtToken}`);
headers.append('Content-Type', 'application/json');




form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = {
        name : document.getElementById("name").value,
        description : document.getElementById("description").value,
        workStart : document.getElementById("work-start").value+":00",
        workEnd : document.getElementById("work-end").value+":00",
    };
    const response =
        await fetch('http://localhost:8080/api/v2/favor', {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(data),
        })
            .catch(error => console.error(error));

});