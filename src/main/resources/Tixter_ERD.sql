--App User Table
drop table if exists tixter_user;
create table tixter_user(
	user_id varchar check(user_id <> ''),
	firstname varchar(25) not null check(firstname <>''),
	lastname varchar(25)not null check(lastname <>''),
	email varchar(255)unique not null check(email <> ''),
	password varchar(25)not null check(password <> ''),
	user_age int not null,
	type_id int not null,
	role_id int not null,
	
	constraint tixter_user_pk
	primary key (user_id)
);

--App User Type Table
drop table if exists user_type;
create table user_type(
	type_id int,
	user_type varchar not null check(user_type <> ''),
	
	constraint user_type_pk
	primary key (type_id)
);

--App User Role Table
drop table if exists user_role;
create table role_type(
	role_id int,
	role_type varchar not null check(role_type<> ''),
	
	constraint role_type_pk
	primary key (role_id)
);

--User Tickets: Active User Tickets
drop table if exists user_tickets;
create table user_tickets(
	user_id varchar check(user_id <> ''),
	ticket_id varchar check(ticket_id <> ''),
	
	constraint user_fk
	foreign key (user_id)
	references tixter_user,
	
	constraint ticket_fk
	foreign key (ticket_id)
	references tickets
);

--Tickets
drop table if exists tickets;
create table tickets(
	ticket_id varchar check(ticket_id<> ''),
	publisher varchar not null check(publisher<> ''),
	ticket_name varchar not null check(ticket_name <>''),
	start_time timestamp not null, 
	price int,
	available int not null,
	movie_id varchar not null,
	
	constraint tickets_pk
	primary key (ticket_id),
	
	constraint target_movie
	foreign key (movie_id)
	references movies,
	
	constraint price_fk
	foreign key (price)
	references prices
);

--Table of available movies
drop table if exists movies;
create table movies(
	movie_id varchar check(movie_id <> ''),
	movie_name varchar not null check(movie_name <> ''),
	
	constraint movies_pk
	primary key (movie_id)
);

--Template Price Table
drop table if exists prices;
create table prices(
	price_id int,
	reg_price decimal not null,
	student_price decimal not null,
	military_price decimal not null,
	senior_price decimal not null,
	dicount_price decimal not null,
	employee_price decimal not null,
	
	constraint prices_pk
	primary key (price_id)
);



