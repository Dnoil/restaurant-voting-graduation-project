# Restaurant Voting Graduation Project
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/00c51a6d85b0478aa0bd37ee05e4a6e2)](https://app.codacy.com/gh/Dnoil/restaurant-voting-graduation-project/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)

## Task

Design and implement a REST API using Spring-Boot/Spring Data JPA without frontend.

The task is:

Build a voting system for deciding where to have lunch.

* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote for a restaurant they want to have lunch at today
* Only one vote counted per user
* If user votes again the same day:
  * If it is before 11:00 we assume that he changed his mind.
  * If it is after 11:00 then it is too late, vote can't be changed
* Each restaurant provides a new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it (better - link to Swagger).

P.S.: Make sure everything works with latest version that is on github :)
P.P.S.: Assume that your API will be used by a frontend developer to build frontend on top of that.

## Swagger Documentation
http://localhost:8080/swagger-ui/index.html