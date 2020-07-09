drop table user;
CREATE TABLE user (
	id bigint primary key auto_increment,
	username varchar(200) unique not null,
	password varchar(200) not null,
	version bigint not null default 0,
	create_at timestamp,
	update_at timestamp
);
INSERT INTO user VALUES (1, "user", "user", 0, now(), now());
INSERT INTO user VALUES (2, "admin", "admin", 0, now(), now());

drop table role;
CREATE TABLE role (
	id bigint primary key auto_increment,
	username varchar(200) not null,
	role varchar(200) not null,
	version bigint not null default 0,
	create_at timestamp,
	update_at timestamp,
	unique(username, role)
);
INSERT INTO role VALUES (1, "user", "USER", 0, now(), now());
INSERT INTO role VALUES (2, "admin", "ADMIN", 0, now(), now());
INSERT INTO role VALUES (3, "admin", "USER", 0, now(), now());