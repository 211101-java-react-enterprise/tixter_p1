--App User Table
drop table if exists tixter_users;
create table tixter_users(
	user_id varchar check(user_id <> ''),
	firstname varchar(25) not null check(firstname <>''),
	lastname varchar(25)not null check(lastname <>''),
	email varchar(255)unique not null check(email <> ''),
	password varchar(25)not null check(password <> ''),
	user_age int not null,
	type_id int not null,
	role_id int not null,
	
	constraint tixter_users_pk
	primary key (user_id)
);

--App User Type Table
drop table if exists user_types;
create table user_types(
	type_id int,
	user_type varchar not null check(user_type <> ''),

	constraint user_types_pk
	primary key (type_id)
);

--App User Role Table
drop table if exists role_types;
create table role_types(
	role_id int,
	role_type varchar not null check(role_type<> ''),

	constraint role_types_pk
	primary key (role_id)
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
	name varchar not null,
	price decimal not null,

	constraint prices_pk
	primary key (price_id)
);

--Tickets
drop table if exists tickets;
create table tickets(
	ticket_id varchar check(ticket_id<> ''),
	publisher_id varchar not null check(publisher_id<> ''),
	ticket_name varchar not null check(ticket_name <>''),
	start_time timestamp,
	price int,
	available int,
	movie_id varchar,

	constraint tickets_pk
	primary key (ticket_id),

	constraint target_movie
	foreign key (movie_id)
	references movies,

	constraint price_fk
	foreign key (price)
	references prices
);

--User Tickets: Active User Tickets
drop table if exists user_tickets;
create table user_tickets(
	user_id varchar check(user_id <> ''),
	ticket_id varchar check(ticket_id <> ''),

	constraint user_fk
	foreign key (user_id)
	references tixter_users,

	constraint ticket_fk
	foreign key (ticket_id)
	references tickets
);

insert into prices (price_id, name, price)
values (0, 'Regular Price', 9.99),
		(1, 'Student Price', .99),
		( 2, 'Military Price', .99),
		( 3, 'Senior Discount', .99),
		( 4, 'Matinee Price', .99),
		( 5, 'Employee Discount', .01)
		

































