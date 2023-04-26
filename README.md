# Ticket System API #

This API allows you to create, manage and user a ticket system at your own accord 
## Authentication ##



## Endpoints ##

To interact whit anything in the api you need to authenticate yourself.

POST `/api/vi/auth/authenticate`

The request body needs to be in JSON format and iclude the following properties:

- `email` - String
- `password` - String

Example

```
{
    "email":"ivoAdmin@mail.com",
    "password":"12345678"
}
```

The response body will contain the access token and information about the user. The access token is valid for 12 hours.

### Article ###

GET `/api/v2/article`

Returns a list of Articles. Requires authentication.

POST `/api/v2/article`

Allows you to submit a new article. Requires authentication.

The request body needs to be in JSON format and include the following properties:

- `name`- Long - Requerd
- `description`- String - Requerd
- `workStart` - SQLTime `HH:mm:ss` format -Requerd
- `wrokEnd` - SQlTime `HH:mm:ss` format -Requerd
- `type` - String -Requerd

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

PUT `/api/v2/article/:articleId`

Allows you to alter an article that has bean created. Requirs authentication.

The request body needs to be in JSON format and include the following properties:

- `name`- Long - Requerd
- `description`- String - Requerd
- `workStart` - SQLTime `HH:mm:ss` format -Requerd
- `wrokEnd` - SQlTime `HH:mm:ss` format -Requerd
- `type` - String -Requerd

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

PUT `/addFavor/:articleId/:favorId`

Returns the id of the article that has bean updated.

### Counter ###

GET `/api/v2/counter`

Returns a list of counters. Requires authentication.

POST `api/v2/counter`

Allows for the creation of new counters.

The request body needs to be in JSON format and include the following properties:

- `name` - String - Required
- `discription` -String - Required
- `number` - Integer - Required

Example

```
{
    "name":"counte1",
    "description":"Some description",
    "number":1
}
```
The response body is the id of the counter and a massage.

### Favor ###

### Favor Type ###

### Machine ###

### Personal Ticket ###

### Ticket ###

### Tciket Type ###

### User ###