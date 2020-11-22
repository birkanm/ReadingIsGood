# ReadingIsGood

* Aspect Oriented Programming is used with default logging mechanism to log what was happened on Specific entities. (such as Book and Order) more generic crossfunctional mechanism could easily build on AOP.  
* Standart Html headers & codes used in Error cases no special class implemented, UI side should parse error message
* Validation done by javax validation, all user inputs fields intended to validate  

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

Address: http://localhost:8080/h2-console
