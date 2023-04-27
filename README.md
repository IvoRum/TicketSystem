# Ticket System API #

This API allows you to create, manage and user a ticket system at your own accord 

## Content List ##

[Sequence](https://github.com/IvoRum/TicketSystem/blob/main/Documents/SequenceDiagram.md)
[Database Diagram](https://github.com/IvoRum/TicketSystem/blob/main/Documents/DatabaseDiagram.md)


## TO DO ##

<details>
    <summary>List</summary>
    <p>Authomathed Postvamn test for generating a lot of randome tickets</p>
    <p>Queing system</P>
    <p>Add PATCH and DELETE mappings</p>
    <P>User loget to a serten counter to work</p>
</details>

## Authentication ##

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

## Endpoints ##

1. [Article](#article "Goto Article")
2. [Favor](#favor "Goto Favor")
3. [Favor-Type](#favor-type "Goto Favor type")
4. [Machine](#machine "Goto Machine")
5. [Personal-Ticket](#personal-ticket "Goto Personal ticket")
6. [Ticket](#ticket "Goto ticket")
7. [Ticket-Type](#tciket-type "Goto Ticket type")
8. [User](#user "Goto User")


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

PATCH `api/v2/counter/:counterId`

Allow for the updating of a existing counter.
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
The request body needs to be in JSON format and include the following properties:

PUT `api/v2/counter/add/ticket/:favorId/:counterId`

To put a new favor in the counter options. Requires authentication.

### Favor ###

GET `api/v2/favor`

Returns a list of favors. Requires authentication.

POST `api/v2/favor`

Allows for the creation of a favor. Requirs authentication.
The request body needs to be in JSON format and include the following properties:

- `name` - String - Required
- `discription` -String - Required
- `workStart` - SQLTime `HH:mm:ss` format - Requerd
- `wrokEnd` - SQlTime `HH:mm:ss` format - Requerd
- `idsOfTypeOfFavors` - Integer Array - Requerd

Example

```
{   
    "name":"AdminWork",
    "description":"some description",
    "workStart":"07:00:00",
    "workEnd":"19:00:00",
    "idsOfTypeOfFavors":[1]
}
```
The response body is the id of the favor and a massage.

PATCH `api/v2/favor/:favorName`

Allows for the updating of the favor.
The request body needs to be in JSON format and include the following properties:

- `name` - String - Required
- `discription` -String - Required
- `workStart` - SQLTime `HH:mm:ss` format - Requerd
- `wrokEnd` - SQlTime `HH:mm:ss` format - Requerd
- `idsOfTypeOfFavors` - Integer Array - Requerd

Example

```
{   
    "name":"AdminWork",
    "description":"some description",
    "workStart":"07:00:00",
    "workEnd":"19:00:00",
    "idsOfTypeOfFavors":[1]
}
```
The response body is the id of the favor and a massage.

### Favor Type ###

GET `api/v2/favortype`

Returns a list of favors. Requires authentication.

POST `api/v2/favortype`

Allows for the creation of a favor type. Requirs authentication.
The request body needs to be in JSON format and include the following properties:

- `name` - String - Required
- `discription` -String - Required

Example

```
{
    "name":"Adminstation",
    "description":"some description"
}
```
The response body is the id of the favor type and a massage.

PATCH `api/v2/favortype/:favorName`

Allows for the creation of a favor type. Requirs authentication.
The request body needs to be in JSON format and include the following properties:

- `name` - String - Required
- `discription` -String - Required

Example

```
{
    "name":"Adminstation",
    "description":"some description"
}
```
The response body is the id of the favor type and a massage.

### Machine ###

GET `api/v1/machine`

Returns a list of machines. Requires authentication.

POST `api/v1/machine`

Allows for the creation of a machine. Requirs authentication.
The request body needs to be in JSON format and include the following properties:

- `name` - String - Required
- `type` -String - Required
- `favorId` -Long - Required

Example

```
{
    "name":"Roller",
    "type":"Gate",
    "favorId":1
}
```
The response body is the id of the machine and a massage.

PATCH `api/v1/machine/:machineName`

Allows for the creation of a machine. Requirs authentication.
The request body needs to be in JSON format and include the following properties:

- `name` - String - Required
- `type` -String - Required
- `favorId` -Long - Required

Example

```
{
    "name":"Roller",
    "type":"Gate",
    "favorId":1
}
```
The response body is the id of the machine and a massage.

### Personal Ticket ###

GET `api/v2/personalticket`

Returns a list of personal tickets. Requires authentication.

POST `api/v2/personalticket`

Allows for the creation of a personal ticket. Requirs authentication.
The request body needs to be in JSON format and include the following properties:

- `issueTime` - SQLTime `HH:mm:ss` format - Requerd

Example

```
{
    "issueTime":"12:00:00"
}
```
The response body is the id of the personal ticket and a massage.

PATCH `api/v2/personalticket/:personalTicketName`

Allows for the creation of a personal ticket. Requirs authentication.
The request body needs to be in JSON format and include the following properties:

- `name` - String - Required
- `type` -String - Required
- `favorId` -Long - Required

Example

```
{
    "name":"Roller",
    "type":"Gate",
    "favorId":1
}
```
The response body is the id of the personal ticket and a massage.

PUT `api/v2/finish/:ticketNumber`

Simple put just to finish a tickets on the name of the loged user. Reqirs Authentication.


### Ticket ###

GET `api/v2/ticket`

Returns a list of tickets. Requires authentication.

POST `api/v2/ticket`

Allows for the creation of a ticket. Requirs authentication.
The request body needs to be in JSON format and include the following properties:

- `name` - String - Requerd
- `workStart`- SQLTime `HH:mm:ss` format - Requerd
- `workEnd` - SQLTime `HH:mm:ss` format - Requerd
- `favorId` - Long - Requerd
- `typeId` - Long -Requerd

Example

```
{
    "name":"ticketExample",
    "workStart":"07:00:01",
    "workEnd":"19:00:02",
    "favorId":1,
    "typeId":1
}
```
The response body is the id of the ticket and a massage.

PATCH `api/v2/ticket/:ticketName`

Allows for the update of a ticket. Requirs authentication.
The request body needs to be in JSON format and include the following properties:

- `name` - String - Requerd
- `workStart`- SQLTime `HH:mm:ss` format - Requerd
- `workEnd` - SQLTime `HH:mm:ss` format - Requerd
- `favorId` - Long - Requerd
- `typeId` - Long -Requerd

Example

```
{
    "name":"ticketExample",
    "workStart":"07:00:01",
    "workEnd":"19:00:02",
    "favorId":1,
    "typeId":1
}
```
The response body is the id of the ticket and a massage.

### Tciket Type ###

GET `api/v2/tickettype`

Returns a list of ticket types. Requires authentication.

POST `api/v2/tickettype`

Allows for the creation of a ticket type. Requirs authentication.
The request body needs to be in JSON format and include the following properties:

- `name` - String - Requerd
- `description` - String - Requerd

Example

```
{
    "name":"Adminstation",
    "description":"some description"
}
```
The response body is the id of the ticket type and a massage.

PATCH `api/v2/tickettype/:ticketTypeName`

Allows for the update of a ticket type. Requirs authentication.
The request body needs to be in JSON format and include the following properties:

- `name` - String - Requerd
- `description` - String - Requerd

Example

```
{
    "name":"Adminstation",
    "description":"some description"
}
```
The response body is the id of the ticket type and a massage.

### User ###

GET `/api/v1/user`

Returns a list of ticket types. Requires authentication.

POST `/api/v1/user/register`

Allows for the registe a user. Requirs authentication.
The request body needs to be in JSON format and include the following properties:

- `firstname` - String - Requerd
- `lastname` - String -Requerd
- `email` - String - Requerd
- `role` - String - Requerd
- `password`- String -Requerd

Example

```
{
    "firstname":"Ivo",
    "lastname":"Rumenov",
    "email":"ivoUser@mail.com",
    "role": "USER",
    "password":"12345678"
}
```
The response body is the id of the ticket type and a massage.

PUT `/api/v1/user/register/addCounter/:counterId`

Allows to add a new counter to the users profile. Requers authentication.

PUT `/api/v1/user/register/addCounter/:favorTypeId`

Allows to add a new facor type to the users profile. Requers authentication.





-------------------
> You have power over your mind - not outside events. Realize this, and you will find strength. -
Marcus Aurelius, Meditations