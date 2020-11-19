INSERT INTO CUSTOMER (PKID, NAME, SURNAME, EMAIL, PHONE) VALUES
  (1, 'Alice', 'Jason', 'alija@example.com', +16192063882),
  (2, 'Bob', 'Tom', 'btom@example.com', +446192063882 );

INSERT INTO BOOK (PKID, NAME, AUTHOR, PRICE, STOCK, VERSION) VALUES
(1, 'Clean Code', 'Robert C. Martin', 50.0, 5, 1 );

INSERT INTO PURCHASE_ORDER (PKID, ORDERID, ORDER_DATE, ORDER_STATUS, COMPLETE_DATE) VALUES
(1, 19110, CURRENT_DATE(), 'COLLECTING', null );

INSERT INTO ORDER_BOOK (PKID, FK_ORDERID, FK_BOOKID) VALUES
(1, 1, 1);

INSERT INTO CUSTOMER_ORDER (PKID, FK_CUSTOMERID, FK_ORDERID,) VALUES
(1, 2, 1);

