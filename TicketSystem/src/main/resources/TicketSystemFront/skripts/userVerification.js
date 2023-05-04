const form=document.querySelector('form')
/*
form.addEventListener('submit', async(e)=>{
    const data=new FormData(form);

    const response=await fetch('localhost:8080/api/vi/auth/authenticate',{
        method:'POST',
        headers:{
            'Content-Type':'application/json'
        },
        body:JSON.stringify({
            email: data.get('email'),
            password: data.get('pass')
        })
    })  .then((response) => response.json())
    .then((json) => console.log(json));
});
*/
try {
    const response = await fetch("url", { 
      method: 'POST',
      headers: { 'Content-Type':'application/json' },
      body: { 
        email: data.get('email'),
      password: data.get('pass') 
    }});
    
    if(response.ok) {
      const json = await response.json();
      console.log(json);
    }
    
    throw new Error('Fetch failed');
    } catch(error) {
    console.log(error);
    }
