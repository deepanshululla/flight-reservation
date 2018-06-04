## Airline E-Commerce website using Java Spring boot and thymeleaf

- Created a web application where logged in users as well as search and book flights.
- Added feature to register new users and implement login.
- Used Spring Data JPA(hibernate) as an ORM to interact with the MYSQL database.
- Designed front end templates using thymeleaf and styled them using Bootstrap and Css.
- Used bower.js to install jquery and bootstrap dependencies.
- Created Integration layer(REST API) for flight checkin microservice to check in passengers
with reservations. 

### Database Model

There are 4 tables

- FLIGHT
- RESERVATION
- USER
- PASSENGER

There is one to one mapping assumed between reservation and passenger as well as reservation
and flight.