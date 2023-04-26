# Ticket System API #

This API allows you to create, manage and user a ticket system at your own accord 

## Endpoints ##

### Article ###

GET 

Returns a list of Articles. Requires authentication.

POST 

Allows you to submit a new article. Requires authentication.

The request body needs to be in JSON format and include the following properties:

-`name`- Long - Requerd
-`description`- String - Requerd
-`workStart` - SQLTime `HH:mm:ss` format -Requerd
-`wrokEnd` - SQlTime `HH:mm:ss` format -Requerd
-`type` - String -Requerd

Example
```
{
    "name":"name",
    "description":"some description",
    "workStart":"08:30:00",
    "workEnd":"15:30:00",
    "type":"type"
}
```
The response body will contain the Article Id.

### Authentication ###

### Counter ###

### Favor ###

### Favor Type ###

### Machine ###

### Personal Ticket ###

### Ticket ###

### Tciket Type ###

### User ###