# Ticket System API #

This API allows you to create, manage and user a ticket system at your own accord 

## Documentation ##

[Sequence](https://github.com/IvoRum/TicketSystem/blob/main/ProjetNotes/SequenceDiagram.md)</p>

[Database Diagram](https://github.com/IvoRum/TicketSystem/blob/main/ProjetNotes/database.md)</p>

[UI/UX](https://github.com/IvoRum/TicketSystem/blob/main/ProjetNotes/UIDisign.md)</p>

[User Personas](https://github.com/IvoRum/TicketSystem/blob/main/ProjetNotes/user-personas.md)</p>

[Queueing logic](https://github.com/IvoRum/TicketSystem/blob/main/ProjetNotes/queeueingLogic.md)</p>
[Ansof Matrix](https://github.com/IvoRum/TicketSystem/blob/main/ProjetNotes/ansof.md)</p>


[Project brief](https://github.com/IvoRum/TicketSystem/blob/main/ProjetNotes/prject-brief.md)</p>
<br>

## Tech Stacks ##

### Database ###

* PostgreSQL 15.2

### Backend ###

* Java 17
* Spring Boot 3
* Spring Security
* JWT
* Mockito
* Hibernate 
* IDE: IntelliJ
* Build tool: Maven
* Server: Tomcat
* Log4j: 1.2.17

### Frontend ###

* IDE: Visual Studio Code
* Vanilla JavaScript
* Server: Vite 4.3.5


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

<br>

## Endpoints ##

1. [Article](#article "Goto Article")
2. [Favor](#favor "Goto Favor")
3. [Machine](#machine "Goto Machine")
4. [Personal-Ticket](#personal-ticket "Goto Personal ticket")
5. [Ticket](#ticket "Goto ticket")
6. [Ticket-Type](#ticket-type "Goto Ticket type")
7. [User](#user "Goto User")
8. [Queue](#queue "Goto Queue")
9. [Draft](#draft "Goto Draft")


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

DELETE `/api/v2/article/:articleId`

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

DELETE `/api/v2/counter/:counterId`

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

DELETE `/api/v2/favor/:favorId`

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

DELETE `/api/v2/machine/:machineId`

### Personal Ticket ###

GET `api/v2/personalticket`

Returns a list of personal tickets. Requires authentication.

GET `api/v2/personalticket/:ticketId`

Returns a list of personal tickets by the ticket id. Requires authentication.

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

DELETE `/api/v2/personalticket/:personalTicketId`

### Ticket ###

GET `api/v2/ticket`

Returns a list of tickets. Requires authentication.

GET `api/v2/ticket/:favorId`

Returns a list of all tickets whit the privided favorId. Requires authentication.

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

PUT `addFavor/:idTicket/:idFavor`

Adds a favor to the ticket. Requirs authentication.

PUT `addFavor/:idTicket/:idPersonalTicket`

Adds a personal ticket to the ticket. Requirs authentication.

DELETE `/api/v2/ticket/:ticketId`

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

DELETE `/api/v2/tickettype/:ticketTypeId`
<br>

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

DELETE `/api/v1/user/:userId`

### Queue ###

GET `api/v1/queue/nextInLine/:counterId`

Gets the next tickt in the line.

PUT `api/v1/queue/open/counter/:counterId`

Opens the counter whit the loged in user.

DELETE `api/v1/queue/close/counter/:counterId`

Closes the counter.

GET `api/v1/queue/waiting ForCounter/:counterId`

Returns all the personal tickets for the counter.

### Draft ###


Authentication for dis endpoint is nopt recluerd.

GET `api/v1/draft/favor`

Gets the Favor so that thay can be printed on the Front-end.

Get `api/v1/draft/waitingForCounter/:counterId`

Returs all tickets in order that are waiting for the counter.

GET `api/v1/draft/lastPersonalTicket`

Retuns the last draftet ticket.

POST `api/v1/draft/:ticketId`

Creates a new persolnal ticket or the so calld 'darafting of a ticket'.
The request body needs to be in JSON format and include the following properties:

- `id` - Long - Not Requerd
- `issueTime` - SQLTime `HH:mm:ss` format - Requerd


Example

```
{
    "id":123123,
    "issueTime":"12:00:00"
}
```


-------------------