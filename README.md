Endpoints:

API 1: To submit a purchase for a ticket

POST: http://localhost:8080/tickets/purchase?section=SECTION_A

Example Request body:

{
"firstName": "Aravindh",
"lastName": "thiyagu",
"email": "Aravindhan@gmail.com"
}

Example response:

{
"id": 3,
"fromLocation": "London",
"toLocation": "France",
"price": 5.0,
"seat": "Seat-40",
"user": {
"id": 3,
"firstName": "Aravindh",
"lastName": "thiyagu",
"email": "Aravindhan@gmail.com"
},
"section": "SECTION_B"
}

API 2: To fetch user details

GET: http://localhost:8080/tickets/3

Example Response:

{
"id": 3,
"fromLocation": "London",
"toLocation": "France",
"price": 5.0,
"seat": "Seat-40",
"user": {
"id": 3,
"firstName": "Aravindh",
"lastName": "thiyagu",
"email": "Aravindhan@gmail.com"
},
"section": "SECTION_B"
}

API 3: To remove user from the train

DELETE: http://localhost:8080/tickets/1

Example Response

User ID - 1 removed successfully.

API 4: To modify user seat

PATCH: http://localhost:8080/tickets/2/seat?newSeat=Seat_18

Example Response:

{
"id": 2,
"fromLocation": "London",
"toLocation": "France",
"price": 5.0,
"seat": "Seat_18",
"user": {
"id": 2,
"firstName": "Kishore",
"lastName": "thiyagu",
"email": "Kishore@gmail.com"
},
"section": "SECTION_B"
}

API 5: To fetch all users in specific section

GET: http://localhost:8080/train/section/SECTION_B

Example Response:

[
{
"id": 2,
"fromLocation": "London",
"toLocation": "France",
"price": 5.0,
"seat": "Seat_18",
"user": {
"id": 2,
"firstName": "Kishore",
"lastName": "thiyagu",
"email": "Kishore@gmail.com"
},
"section": "SECTION_B"
},
{
"id": 3,
"fromLocation": "London",
"toLocation": "France",
"price": 5.0,
"seat": "Seat-40",
"user": {
"id": 3,
"firstName": "Aravindh",
"lastName": "thiyagu",
"email": "Aravindhan@gmail.com"
},
"section": "SECTION_B"
}
]

