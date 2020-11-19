# ReadingIsGood

**Data Model**

Table "Stock" not created, **optimistic lock** will be used in **Book** class to handle stock, Moreover it will help prevent multi transaction at the same time on single book.

No password will be stored in **Customer** table all login request with stored email will be accepted and JWT token will returned

**Issue Tracking & CI Tool**

Github's "Issues" and "Action" mechanism used

**Test**

Standart methods on repositories are not tested.


**Database**

in memory H2 database is used
 
Hint: 

jdbcUrl: jdbc:h2:mem:testdb
console Address: http://localhost:8080/h2-console
