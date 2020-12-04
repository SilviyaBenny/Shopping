
create table product(
   ID  bigint auto_increment,
   NAME VARCHAR(30) NOT NULL,
   QUANTITY INT NOT NULL,
   PRICE DECIMAL(7,2) NOT NULL,
   SKU VARCHAR(30) NOT NULL,
   DEPARTMENT_ID INT ,
   PRIMARY KEY (ID)
);

