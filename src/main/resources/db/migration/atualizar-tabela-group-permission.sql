ALTER TABLE grouping 
ADD module_id integer,
ADD	permission_id integer,
ADD	institution_id integer,
ADD constraint fk_grouping
foreign key (permission_id) references permission (id);


DELETE FROM grouping_permission;

DROP TABLE grouping_permission;

DELETE FROM user_grouping;

DROP TABLE user_grouping;
