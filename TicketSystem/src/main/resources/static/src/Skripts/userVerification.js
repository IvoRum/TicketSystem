function verifu(email,password){

    fetch('https://example.com/login', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
        email: email,
        password: password
    })
    })
    .then(response => {
        if (response.ok) {
        // Login successful, do something
        console.log('Login successful');
        } else {
        // Login failed, handle the error
        console.error('Login failed');
        }
    })
    .catch(error => {
        console.error(error);
    });
}
