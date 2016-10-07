--Cities
INSERT INTO divipola (id, name) VALUES (1, 'VALLE DEL CAUCA');
INSERT INTO divipola (id, name) VALUES (2, 'ANTIOQUIA');
INSERT INTO divipola (id, name) VALUES (3, 'CUNDINAMARCA');
INSERT INTO divipola (id, name) VALUES (4, 'ATLANTICO');
INSERT INTO divipola (id, name, id_divipola) VALUES (100, 'CALI', 1);
INSERT INTO divipola (id, name, id_divipola) VALUES (101, 'PALMIRA', 1);
INSERT INTO divipola (id, name, id_divipola) VALUES (102, 'BUGA', 1);
INSERT INTO divipola (id, name, id_divipola) VALUES (103, 'TULUA', 1);
INSERT INTO divipola (id, name, id_divipola) VALUES (104, 'MEDELLIN', 3);
INSERT INTO divipola (id, name, id_divipola) VALUES (105, 'RIO NEGRO', 2);
INSERT INTO divipola (id, name, id_divipola) VALUES (106, 'BOGOTÁ', 3);
INSERT INTO divipola (id, name, id_divipola) VALUES (107, 'GIRARDOT', 3);
INSERT INTO divipola (id, name, id_divipola) VALUES (108, 'BARRANQUILLA', 4);
INSERT INTO divipola (id, name, id_divipola) VALUES (109, 'PUERTO DE ORO', 4);

--User
INSERT INTO users (address_neihborhood, address_number, address_residential_unit, address_street, aud_usr_modified, is_usr_premium, name, num_id, usr, id_divipola) VALUES ('1ro Mayo', '13b-256', 'Caracolies', 'Cra 64 A', 'admin', false, 'Jerson Viveros', '16942250', 'jerviver21', 100);
INSERT INTO users (address_neihborhood, address_number, address_residential_unit, address_street, aud_usr_modified, is_usr_premium, name, num_id, usr, id_divipola) VALUES ('1ro Mayo', '13b-256', 'Caracolies', 'Cra 64 A', 'admin', false, 'Jerson Viveros', '16942251', 'usuario1', 100);

--Item
INSERT INTO item (auction_end, aud_usr_modified, description, initial_price, is_published, name, version, id_user) VALUES ('2016-12-31 17:00:00', 'jerviver21', 'En perfecto estado', 100000, true, 'Escritorio', 0, 1);

--Bid
insert into bid (amount, create_on, id_item, id_user) values (101000, '2016-12-23 14:00:00', 1, 2);
insert into bid (amount, create_on, id_item, id_user) values (102000, '2016-12-23 14:00:00', 1, 2);
