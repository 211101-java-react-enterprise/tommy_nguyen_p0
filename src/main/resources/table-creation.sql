/*
 * Things we will need in our Banking Database
 * 
 * 	-App Users
 * 		+ id (PK)
 * 		+ first_name
 * 		+ last_name
 * 		+ email
 * 		+ username
 * 		+ password
 * 	-Accounts
 * 		+ id (PK)
 * 		+ balance
 * 		+ customer_id(FK)
 * 		+ type (FK)
 * 	-Account Type
 * 		+ id (PK)
 * 		+ type (FK)
 * 	-Transactions
 * 		+ id (PK)
 * 		+ desc
 * 		+ date
 * 		+ account_id (FK)
 */

drop table if exists transactions;
drop table if exists accounts_types;
drop table if exists accounts;
drop table if exists app_users;

create table app_users (
	id varchar,
	first_name varchar(25) not null,
	last_name varchar(25) not null,
	email varchar(255) unique not null,
	username varchar(20) unique not null,
	password varchar(20) not null,

	constraint app_users_pk
	primary key (id)
);

select * from app_users au;