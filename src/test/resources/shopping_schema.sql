
create table product(
   RECORD_ID VARCHAR(36) NOT NULL ,
   NAME VARCHAR(30) NOT NULL,
   QUANTITY INT NOT NULL,
   PRICE DECIMAL(7,2) NOT NULL,
   SKU VARCHAR(30) NOT NULL,
   DEPARTMENT_ID VARCHAR(36),
   DESCRIPTION VARCHAR(250),
   PRIMARY KEY (RECORD_ID)
   
);

alter table product add CREATED_BY  VARCHAR(30);
alter table product add CREATED_DATE DATE;
alter table product add MODIFIED_BY  VARCHAR(30);
alter table product add MODIFIED_DATE DATE;
commit;

create table department(
	RECORD_ID VARCHAR(36) NOT NULL PRIMARY KEY ,
	DEPARTMENT_NAME VARCHAR(30),
	DESCRIPTION VARCHAR(250)
	);
	
alter table department add CREATED_BY  VARCHAR(30);
alter table department add CREATED_DATE DATE;
alter table department add MODIFIED_BY  VARCHAR(30);
alter table department add MODIFIED_DATE DATE;

commit;
	