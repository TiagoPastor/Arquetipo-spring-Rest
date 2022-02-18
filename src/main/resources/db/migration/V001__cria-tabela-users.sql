CREATE TABLE IF NOT EXISTS users (
    users_id SERIAL CONSTRAINT pk_id_users PRIMARY KEY,
    google_uuid varchar(50),
    ios_uuid varchar(50),
    users_email varchar(50) NOT NULL,
    crypted_pass varchar(700) not null,
    status_ varchar(50) not null default 'Não autorizado',
    users_name varchar(150),
    photo_url varchar(200),    
    UNIQUE(users_email)
);