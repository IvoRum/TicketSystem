// Define the data for the table rows
var data = [
    { name: "John", age: 25, email: "john@example.com" },
    { name: "Jane", age: 30, email: "jane@example.com" },
    { name: "Bob", age: 40, email: "bob@example.com" },
  ];
  
  // Get the table body element
  var tableBody = document.querySelector("#my-table tbody");
  
  // Loop through the data and create table rows
  for (var i = 0; i < data.length; i++) {
    // Create a new table row element
    var row = document.createElement("tr");
  
    // Create table cells and add data to them
    var nameCell = document.createElement("td");
    nameCell.textContent = data[i].name;
  
    var ageCell = document.createElement("td");
    ageCell.textContent = data[i].age;
  
    var emailCell = document.createElement("td");
    emailCell.textContent = data[i].email;
  
    // Add the cells to the row
    row.appendChild(nameCell);
    row.appendChild(ageCell);
    row.appendChild(emailCell);
  
    // Add the row to the table body
    tableBody.appendChild(row);
  }
  