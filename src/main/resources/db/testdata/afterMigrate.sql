TRUNCATE TABLE users, user_grouping, grouping, grouping_permission, permission, oauth_client_details RESTART IDENTITY;

DELETE FROM users;
DELETE FROM user_grouping;
DELETE FROM grouping;
DELETE FROM grouping_permission;
DELETE FROM permission;
DELETE FROM oauth_client_details;

ALTER sequence users_users_id_seq restart with 1;
ALTER sequence grouping_id_seq restart with 1;
ALTER sequence permission_id_seq restart with 1;

INSERT INTO users (google_uuid, ios_uuid, users_email, crypted_pass, status_, users_name, photo_url) VALUES
('hkjhkhkjh', 'dfdfdfdf', 'fernandojrsud@hotmail.com','$2a$12$Bcjm9WHjcSbrmxFyzimdO.zvMLRbsyggwBEc3uKX1rGWrLb3qn/Ru', '1','Fernando', 'https://www.fernandojr.com.br/img/profile.jpg');
INSERT INTO users (google_uuid, ios_uuid, users_email, crypted_pass, status_, users_name, photo_url) VALUES
('hkjhkhkjh', 'dfdfdfdf', 'tiago@hotmail.com','$2a$12$Bcjm9WHjcSbrmxFyzimdO.zvMLRbsyggwBEc3uKX1rGWrLb3qn/Ru', '1','Tiago Pastor', 'https://www.fernandojr.com.br/img/profile.jpg');

INSERT INTO permission (name, description) VALUES ('CONSULTAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite consultar usuários, grupos e permissões');
INSERT INTO permission (name, description) VALUES ('EDITAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite criar ou editar usuários, grupos e permissões');

INSERT INTO grouping (name) VALUES ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');

INSERT INTO grouping_permission (grouping_id, permission_id) VALUES (1, 1), (1, 2), (2, 1), (2, 2), (3, 1); 

INSERT INTO user_grouping (user_id, grouping_id) VALUES (1, 1), (1, 2);

INSERT INTO oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
VALUES (
  'back-web', null, '$2y$12$w3igMjsfS5XoAYuowoH3C.54vRFWlcXSHLjX7MwF990Kc2KKKh72e',
  'READ,WRITE', 'password', null, null,
  60 * 60 * 6, 60 * 24 * 60 * 60, null
);

INSERT INTO oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
VALUES (
  'backanalytics', null, '$2y$12$fahbH37S2pyk1RPuIHKP.earzFmgAJJGo26rE.59vf4wwiiTKHnzO',
  'READ,WRITE', 'authorization_code', 'http://localhost:8003', null,
  null, null, false
);

INSERT INTO oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
VALUES (
  'faturamento', null, '$2y$12$fHixriC7yXX/i1/CmpnGH.RFyK/l5YapLCFOEbIktONjE8ZDykSnu',
  'READ,WRITE', 'client_credentials', null, 'CONSULTAR_AGENDAMENTOS,GERAR_RELATORIOS',
  null, null, false
);