create database cadastro;
create table cliente(
codigo bigint not null auto_increment,
email varchar(200),
senha varchar(200),
nome varchar(100),
cpf varchar(11) ,
rdata datetime,
primary key(codigo)
);