# ReadingIsGood

* Aspect Oriented Programming is used with default logging mechanism to log what was happened on Specific entities. (such as Book and Order) more generic crossfunctional mechanism could easily build on AOP.  
* Standart Html headers & codes used in Error cases no special class implemented, UI side should parse error message
* Validation done by javax validation, all user inputs fields intended to get validated  

**Data Model**

Table "Stock" not created, **optimistic lock** will be used in **Book** class to handle stock, Moreover it will help prevent multi transaction at the same time on single book.

No password will be stored in **Customer** table all login request with stored email will be accepted and JWT token will returned

Please check data diagram under **docs** folder (.png version is available)

**Issue Tracking & CI Tool**

Github's "Issues" and "Action" mechanism used

**Test**

Standart methods on repositories are not tested.

**Database**

in memory H2 database is used
 
_Hint For Connection_: 

jdbcUrl: jdbc:h2:mem:testdb

console is enables, on its default address
 
http://localhost:8080/h2-console


**Authorization**

Program has that functionality after getting JWT token from, http://localhost:8080/login

with an email address (hint: no password required)

  
The example from the website below is used.
 
https://blog.softtek.com/en/token-based-api-authentication-with-spring-and-jwt

**Open Api Specification** 

This feature enabled on default Url
I am unable to activate Authorization button that you should login first and send Authorization token for all requests

**Postman**

You can find created workspace under **docs** folder with the name _"getir-birkan.postman_collection"_
