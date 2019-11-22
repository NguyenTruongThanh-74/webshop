create table role(
id int not null primary key ,
name varchar(100)
);

create table user(
id int not null ,
name varchar(100) not null,
email varchar(100) not null ,
password varchar(200) not null,
CONSTRAINT user_key primary key (id,email)

);

create table userrole(
id int not null,
user_id int not null,
role_id int not null,
foreign key (user_id) references user(id),
foreign key (role_id) references role(id)
);


CREATE TABLE persistent_logins (
    username varchar(64) not null,
    series varchar(64) not null,
    token varchar(64) not null,
    last_used timestamp not null,
    PRIMARY KEY (series)
);

alter table userrole
add primary key (id);


INSERT INTO `role` (`id`, `name`) VALUES
(1, 'admin'),
(2, 'user');