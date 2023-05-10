const form = document.querySelector('#employee-form');
const jwtToken = localStorage.getItem('jwtToken');


let headers = new Headers();
headers.append('Authorization', `Bearer ${jwtToken}`);
headers.append('Content-Type', 'application/json');




form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = {
        firstname: form.elements.firstName.value,
        lastname: form.elements.lastName.value,
        email: form.elements.email.value,
        role: 'USER',
        password: form.elements.password.value
    };
    const response =
        await fetch('http://localhost:8080/api/v1/user/register', {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(data),
        })
            .catch(error => console.error(error));

});



