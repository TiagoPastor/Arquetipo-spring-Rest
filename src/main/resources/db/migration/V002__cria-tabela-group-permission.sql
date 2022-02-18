CREATE TABLE IF NOT EXISTS grouping (
	id SERIAL CONSTRAINT pk_grouping PRIMARY KEY,
	name varchar(60) NOT NULL	
);

CREATE TABLE IF NOT EXISTS grouping_permission (
	grouping_id integer NOT NULL,
	permission_id integer NOT NULL,
	
	PRIMARY KEY	(grouping_id, permission_id)
);

CREATE TABLE IF NOT EXISTS permission (
	id SERIAL CONSTRAINT pk_permission PRIMARY KEY,
	name varchar(100) NOT NULL,
	description varchar(60) NOT NULL	
);

CREATE TABLE IF NOT EXISTS user_grouping (
	user_id integer NOT NULL,
	grouping_id integer NOT NULL,
	
	PRIMARY KEY	(user_id, grouping_id)
);


ALTER TABLE grouping_permission add constraint fk_grouping_permission_permission
foreign key (permission_id) references permission (id);

ALTER TABLE grouping_permission add constraint fk_grouping_permission_grouping
foreign key (grouping_id) references grouping (id);

ALTER TABLE user_grouping add constraint fk_user_grouping_grouping
foreign key (grouping_id) references grouping (id);

ALTER TABLE user_grouping add constraint fk_user_grouping_users
foreign key (user_id) references users (users_id);