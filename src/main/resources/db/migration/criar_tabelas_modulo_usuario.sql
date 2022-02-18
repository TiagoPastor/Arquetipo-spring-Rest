CREATE TABLE IF NOT EXISTS contact (
    id SERIAL CONSTRAINT pk_id_phone PRIMARY KEY,
    phone_country varchar(3), 
    phone_area_code varchar(2), 
    phone_num varchar(15),
	email varchar(50)
);

CREATE TABLE IF NOT EXISTS address_info (
    address_id SERIAL CONSTRAINT pk_id_address_info PRIMARY KEY,
    address_zip varchar(20),
    address_complement varchar(200),
    address_city varchar(150),
    address_fu varchar(2),
    address_number varchar(20),
    address_neighborhood varchar(100),
    address_street varchar(150)
);


CREATE TABLE IF NOT EXISTS institutions (
    institution_id SERIAL CONSTRAINT pk_id_institution PRIMARY KEY,
    institution_name varchar(150) NOT NULL, 
    institution_status varchar(20) NOT NULL, 
    institution_type varchar(50) NOT NULL, 
    reference timestamp NOT NULL default CURRENT_TIMESTAMP, 
    acceptance timestamp, -- uma instituição pode ser cadastrada a revelia
    gov_id varchar(40) NOT NULL, -- cnpj
    logo_url varchar(400),
    more_info varchar(400),
    address_info_id integer REFERENCES address_info ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS institution_divisions (
    institution_division_id SERIAL CONSTRAINT pk_id_institution_division PRIMARY KEY,
    institution_division_name varchar(150) NOT NULL, 
    institution_id integer REFERENCES institutions,
    divisions_email varchar(50)
);


CREATE TABLE IF NOT EXISTS profiles (
    profile_id SERIAL CONSTRAINT pk_id_profile PRIMARY KEY,
    user_id integer REFERENCES users,
    address_info_id integer REFERENCES address_info ON DELETE SET NULL,
    name varchar(150) NOT NULL, 
    short_name varchar(50) NOT NULL, 
    acceptance timestamp,
    email varchar(50),
    photo_url varchar(400),
    more_info varchar(400),
    gov_id varchar(40),
    birthday timestamp,
    gender varchar(20),
	grouping_id integer REFERENCES grouping

);

CREATE TABLE IF NOT EXISTS doctors (
    doctor_id SERIAL CONSTRAINT pk_id_doctor PRIMARY KEY,
    doctor_profile_id integer REFERENCES profiles,
    class_id varchar(40), -- crm
    speciality varchar(40)
);

CREATE TABLE IF NOT EXISTS administrators (
    administrator_id SERIAL CONSTRAINT pk_id_administrator PRIMARY KEY,
    profile_id integer REFERENCES profiles
);


CREATE TABLE IF NOT EXISTS collaborators (
    collaborator_id SERIAL CONSTRAINT pk_id_collaborator PRIMARY KEY,
    collaborator_profile_id integer REFERENCES profiles,
    collaborator_role varchar(50) NOT NULL default 'Atendente', -- perfis profissionais médico, administrador, etc
    collaborator_default boolean NOT NULL default false,
    collaborator_status varchar(20) NOT NULL default 'Pendente', 
    institution_id integer REFERENCES institutions,
    institution_division_id integer REFERENCES institution_divisions -- a instituição onde ele atende
);


CREATE TABLE IF NOT EXISTS modules (
    module_id SERIAL CONSTRAINT pk_id_module PRIMARY KEY,
    name varchar(50) NOT NULL 
 
);

CREATE TABLE IF NOT EXISTS preferences (
    module_id SERIAL REFERENCES modules,
    contract_hash varchar(150) NOT NULL, 
    institution_id integer REFERENCES institutions,
    profile_id integer REFERENCES profiles
);


CREATE TABLE IF NOT EXISTS profiles_contact (
	profile_id integer NOT NULL,
	contact_id integer NOT NULL
);

ALTER TABLE profiles_contact add constraint fk_profiles_contact_profiles
foreign key (profile_id) references profiles (profile_id);

ALTER TABLE profiles_contact add constraint fk_profiles_contact_contact
foreign key (contact_id) references contact (id);


CREATE TABLE IF NOT EXISTS institution_divisions_contact (
	institution_division_id integer NOT NULL,
	contact_id integer NOT NULL
);

ALTER TABLE institution_divisions_contact add constraint fk_institutions_contact_divisions_institutions
foreign key (institution_division_id) references institution_divisions (institution_division_id);

ALTER TABLE institution_divisions_contact add constraint fk_institutions_contact_divisions
foreign key (contact_id) references contact (id);


CREATE TABLE IF NOT EXISTS institutions_contact (
	institution_id integer NOT NULL,
	contact_id integer NOT NULL
);

ALTER TABLE institutions_contact add constraint fk_institutions_contact_istitutions
foreign key (institution_id) references institutions (institution_id);

ALTER TABLE institutions_contact add constraint fk_institutions_contact
foreign key (contact_id) references contact (id);