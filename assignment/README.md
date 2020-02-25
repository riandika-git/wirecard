#Open Swagger UI:
- To access the API using swagger, open your browser and go to [Swagger UI](http://localhost:8081/swagger-ui.html)


#User API
- Expand user-controller
- Select any of five operations available

**(1) Get user by id**
- Click "Try it out"
- type the id e.g 5
- Click "Execute"

**(2) Save new user record**
- Click "Try it out"
- insert the data in json format e.g: 

```sh
{
  "date": "2020-02-24 10:15:11",
  "name": "Michael",
  "type": 3
}
```
- Click "Execute"
		
**(3) Delete user by id**
- Click "Try it out"
- type the id e.g 5
- Click "Execute"

**(4) Get user list**
- Click "Try it out"
- type date to be searched in (yyyy-mm-dd) format
- type name to be searched
- set page number to be displayed
- set maksimum rows of data to be displayed in one page
- set the field to be sorted by
- set the sort type (asc / desc)
- Click "Execute"

**(5) Update user record**
- Click "Try it out"
- insert the information to updated in json format e.g: 

```sh
{
  "id": 4,
  "date": "2020-02-24 10:15:11",
  "name": "Michael",
  "type": 3
}
```
- Click "Execute"


#User Type API	
- Expand user-type-controller
- Select any of five operations available

**(1) Get user type by id****
- Click "Try it out"
- type the id e.g 5
- Click "Execute"

**(2) Save new user type record**
- Click "Try it out"
- insert the data in json format e.g: 

```sh
{
  "name": "Michael"
}
```
- Click "Execute"

**(3) Delete user type by id**
- Click "Try it out"
- type the id e.g 5
- Click "Execute"

**(4) Get user type list**
- Click "Try it out"
- type name to be searched
- set page number to be displayed
- set maksimum rows of data to be displayed in one page
- set the field to be sorted by
- set the sort type (asc / desc)
- Click "Execute"

**(5) Update user type record**
- Click "Try it out"
- insert the information to updated in json format e.g:

```sh
{
  "id": 4,
  "name": "student"
}
```
- Click "Execute"
