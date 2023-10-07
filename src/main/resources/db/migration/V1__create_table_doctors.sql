create table doctors(
id bigint PRIMARY KEY auto_increment not null,
name VARCHAR(100) not null,
email VARCHAR(100) not null unique,
dni VARCHAR(10) not null unique,
speciality VARCHAR(100) not null,
street VARCHAR(100) not null,
district VARCHAR(100) not null,
city VARCHAR(100) not null,
numeration VARCHAR(20),
complement VARCHAR(100)
)