Notes:
=============

- To access the API using swagger, open your browser and go to
    http://localhost:8081/swagger-ui.html

- Expand user-controller
	- Select any of five operations available
		- Get user by id
			- Click "Try it out"
			- type the id e.g 5
			- Click "Execute"
		- Save new user record
			- Click "Try it out"
			- insert the data in json format
				e.g: {
					  "date": "2020-02-24 10:15:11",
					  "name": "Michael",
					  "type": 3
					}
			- Click "Execute"
		- Delete user by id
			- Click "Try it out"
			- type the id e.g 5
			- Click "Execute"
		- Get user list
			- Click "Try it out"
			- type date to be searched in (yyyy-mm-dd) format
			- type name to be searched
			- set page number to be displayed
			- set maksimum rows of data to be displayed in one page
			- set the field to be sorted by
			- set the sort type (asc / desc)
			- Click "Execute"
		- Update user record
			- Click "Try it out"
			- insert the information to updated in json format
				e.g: {
					  "id": 4,
					  "date": "2020-02-24 10:15:11",
					  "name": "Michael",
					  "type": 3
					}
			- Click "Execute"
			
- Expand user-type-controller
	- Select any of five operations available
		- Get user type by id
			- Click "Try it out"
			- type the id e.g 5
			- Click "Execute"
		- Save new user type record
			- Click "Try it out"
			- insert the data in json format
				e.g: {
					  "name": "Michael"
					}
			- Click "Execute"
		- Delete user type by id
			- Click "Try it out"
			- type the id e.g 5
			- Click "Execute"
		- Get user type list
			- Click "Try it out"
			- type name to be searched
			- set page number to be displayed
			- set maksimum rows of data to be displayed in one page
			- set the field to be sorted by
			- set the sort type (asc / desc)
			- Click "Execute"
		- Update user type record
			- Click "Try it out"
			- insert the information to updated in json format
				e.g: {
					  "id": 4,
					  "name": "student"
					}
			- Click "Execute"