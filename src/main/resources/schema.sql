create table if not exists user (
    id bigint not null auto_increment,
    username varchar(255) not null,
    password varchar(1024) not null,
    primary key (id)
);