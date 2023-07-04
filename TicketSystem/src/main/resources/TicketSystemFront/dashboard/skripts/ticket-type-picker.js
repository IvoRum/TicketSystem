const jwtToken = localStorage.getItem('jwtToken');
const form = document.querySelector('#employee-form');

let headers = new Headers();
headers.append('Authorization', `Bearer ${jwtToken}`);

const select = document.getElementById('favorType');
const select1 = document.getElementById('ticketType');
const select2 = document.getElementById('counter');

// Fetch data using GET request
fetch('http://localhost:8080/api/v2/favor',{
    method: 'GET',
    headers: headers,
})
  .then(response => response.json())
  .then(data => {
    data.forEach(item => {
      const option = document.createElement('option');
      option.text = item.name;
      option.value = item.id;
      select.appendChild(option);
    });
  })
  .catch(error => {
    console.error('Error:', error);
  });


  fetch('http://localhost:8080/api/v2/tickettype',{
    method: 'GET',
    headers: headers,
})
  .then(response => response.json())
  .then(data => {
    data.forEach(item => {
      const option = document.createElement('option');
      option.text = item.name;
      option.value = item.id;
      select1.appendChild(option);
    });
  })
  .catch(error => {
    console.error('Error:', error);
  });


  fetch('http://localhost:8080/api/v2/counter',{
    method: 'GET',
    headers: headers,
})
  .then(response => response.json())
  .then(data => {
    data.forEach(item => {
      const option = document.createElement('option');
      option.text = item.name;
      option.value = item.id;
      select2.appendChild(option);
    });
  })
  .catch(error => {
    console.error('Error:', error);
  });






  form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const selectedIds = [];
  
    const selectTags = document.querySelectorAll('select');
    selectTags.forEach(select => {
      const selectedOption = select.options[select.selectedIndex];
  
      const selectedId = selectedOption.value;
      selectedIds.push(selectedId);
    });
  
    console.log(selectedIds);
    console.log(selectedIds[1]);
    console.log(selectedIds[2]);

    const data = {
        name: document.getElementById("ticketName").value,
      workStart : document.getElementById("work-start").value+":00",
      workEnd : document.getElementById("work-end").value+":00",
        favorId:Number(selectedIds[1]),
        typeId:Number(selectedIds[0])
    };
    let headers1 = new Headers();
    headers1.append('Authorization', `Bearer ${jwtToken}`);
    headers1.append('Content-Type', 'application/json');
  let newieCreatedTicketId;
    const response =
    await fetch('http://localhost:8080/api/v2/ticket', {
        method: 'POST',
        headers: headers1,
        body: JSON.stringify(data),
    }).then(response => response.json())
    .then(data => {
      newieCreatedTicketId=data.id;
      console.log(data.id);
    })
    .catch(error => console.error(error));

    const response1 =
    await fetch('http://localhost:8080/api/v2/ticket/addFavor/'+newieCreatedTicketId+'/'+selectedIds[1], {
        method: 'PUT',
        headers: headers1,
        body: JSON.stringify(data),
    }).catch(error => console.error(error));

    
      
    const response2 =
    await fetch('http://localhost:8080/api/v2/counter/add/ticket/'+selectedIds[1]+'/'+selectedIds[2], {
        method: 'PUT',
        headers: headers1,
        body: JSON.stringify(data),
    }).catch(error => console.error(error));
    
  });