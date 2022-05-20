Create database cmsdb;
use cmsdb;


create table CustomerDetails(
cust_id varchar(20) not null,
cust_name varchar(20) not null,
phone_no int(10),
Email varchar(25),
wallet_balance decimal(10,2),
password varchar(25),
constraint c_pk primary key(cust_id)
);

describe CustomerDetails;


Create Table FoodDescription(
food_id int(11) PRIMARY KEY,
food_name varchar(25),
food_price decimal(10,2)
);

describe FoodDescription;


create table login(
login_id varchar(25),
password varchar(25));

describe login;


create table login_details(
user_id varchar(20) ,
password varchar(20),
FOREIGN KEY (user_id) REFERENCES CustomerDetails(cust_id));

describe login_details;


create table vendor(
vendor_id int(11) Primary key,
vendora_name varchar(25),
vendor_phone int(11),
vendor_specilaisation varchar(25),
password varchar(25));

describe vendor;


create table order_details(
order_id int(11) Primary key AUTO_INCREMENT,
vendor_id int(11),
cust_id varchar(20),
food_id int(11),
order_qunatity int(11),
ETA date,
order_date date,
order_value decimal(7,2),
order_status varchar(20),
FOREIGN KEY (vendor_id) REFERENCES vendor(vendor_id),
FOREIGN KEY (cust_id) REFERENCES CustomerDetails(cust_id),
FOREIGN KEY (food_id) REFERENCES FoodDescription(food_id));




describe order_details;


insert into CustomerDetails values(145,'Anju',1234,'anju@mail.com',1234.00,'anju123'),
   (198,'Anju',1234,'anju@mail.com',1234.00,'anju123'),
   (2345,'Ashu',4560,'ashu@mail.com',1299.00,'Ashu@03'),
   (6745,'Sai',7890,'sai@mail.com',1934.00,'Sai@001'),
   (589,'Ashish',3690,'Ashish1@mail.com',1554.00,'Ashish#2020'),
   (789,'Anjali',2580,'anjalii@mail.com',1014.00,'Anjali&1123'),
   (1010,'Rohan',1470,'rahan68@mail.com',1255.00,'Rohan@177'),
   (4101,'Babu',7852,'babu100@mail.com',1004.00,'Babu@1010');
   
   
insert into login_details values('145','SYDNEY'),
('198','Aas15'),
('2345','Sass41'),
('6745','Wass15');

insert into vendor values(101,'Anju',11411,'Chinese','P1236'),
(102,'Ashish',11412,'North Indian','As1236'),
(103,'Sai',11413,'South Indian','Sai147'),
(104,'Babu',11414,'Non-Veg','Babu@123');

insert into FoodDescription values(114,'Dosa',50.00),
(115,'Paratha',60.00),
(116,'Chicken ',120.00),
(117,'Rice',80.00);

insert into order_details values(1,101,'145',114,2,NULL,NULL,35.00,'Placed'),
(2,102,'198',115,2,NULL,NULL,44.00,'Placed'),
(3,103,'2345',116,2,NULL,NULL,56.00,'Placed'),
(4,104,'6745',117,2,NULL,NULL,28.00,'Placed');


select *from order_details;
select *from FoodDescription;
select *from vendor;
select *from login_details;
select *from CustomerDetails;