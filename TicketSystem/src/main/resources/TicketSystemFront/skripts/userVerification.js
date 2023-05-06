const form=document.querySelector('form')
//var cors = require('cors');

form.addEventListener('submit', async(e)=>{
  e.preventDefault();
  const data=new FormData(form);
  
    const response = 
    await fetch('http://localhost:8080/api/vi/auth/authenticate', { 
      method: 'POST',
      headers: { 
        'Content-Type':'application/json', 
      },
      body: JSON.stringify( { 
        email: data.get('email'),
        password: data.get('pass')
    }),
  });
  console.log(response);   
    if(response.ok) {
      const json = await response.json();
      console.log(json);
    }
    
    const result=document.querySelector('#result');
    
    console.log(result);
    console.log(data.get('email'));
    console.log(data.get('pass'));
  });
  
  
