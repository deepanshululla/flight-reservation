
use reservation;

CREATE TABLE ROLE
(
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(20),
PRIMARY KEY (ID)
);

create table user_role(
user_id int,
role_id int,
FOREIGN KEY (user_id)
REFERENCES user(id),
FOREIGN KEY (role_id)
REFERENCES role(id)
);

insert into role values(1,'ADMIN');

insert into role VALUES (2,'PASSENGER')

insert into user_role values(1,1);

select * from user;

select * from role;

select * from user_role;

drop table role;

drop table user_role;



