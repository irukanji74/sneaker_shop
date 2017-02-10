SET FOREIGN_KEY_CHECKS=0; DROP TABLE users; SET FOREIGN_KEY_CHECKS=1;
drop table authorities;

create table users (
    username varchar(50) not null primary key,
    password varchar(50) not null,
    enabled boolean not null
) engine = InnoDb;

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    foreign key (username) references users (username),
    unique index authorities_idx_1 (username, authority)
) engine = InnoDb;

create unique index ix_auth_username on authorities (username, authority);

