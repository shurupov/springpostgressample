
CREATE TABLE if not exists sb_department
(
  id serial PRIMARY KEY,
  name varchar(255),
  description varchar(255)
);

INSERT INTO sb_department (id, name, description) VALUES (1, 'Development Department', 'Develops very good product');
INSERT INTO sb_department (id, name, description) VALUES (2, 'Sales Department', 'Sells very good product');
INSERT INTO sb_department (id, name, description) VALUES (3, 'QA Department', 'Tests very good product');
INSERT INTO sb_department (id, name, description) VALUES (4, 'Fun Department', 'Makes fun');

CREATE TABLE if not exists sb_user
(
  id serial PRIMARY KEY,
  department_id int,
  first varchar(255),
  last varchar(255)
);

INSERT INTO sb_user (department_id, first, last) VALUES (1, 'Tom', 'Smith');
INSERT INTO sb_user (department_id, first, last) VALUES (1, 'Jack', 'Bridge');
INSERT INTO sb_user (department_id, first, last) VALUES (1, 'Nicole', 'Bash');
INSERT INTO sb_user (department_id, first, last) VALUES (2, 'Anthony', 'Randle');
INSERT INTO sb_user (department_id, first, last) VALUES (2, 'Diana', 'Wilson');
INSERT INTO sb_user (department_id, first, last) VALUES (2, 'Mario', 'Wamsley');
INSERT INTO sb_user (department_id, first, last) VALUES (3, 'Ronald', 'Loy');
INSERT INTO sb_user (department_id, first, last) VALUES (3, 'Matilda', 'Yuryeva');
INSERT INTO sb_user (department_id, first, last) VALUES (3, 'Bandobras', 'Maggot');
INSERT INTO sb_user (department_id, first, last) VALUES (4, 'Fai', 'Tung');
INSERT INTO sb_user (department_id, first, last) VALUES (4, 'Justus', 'Spapens');
INSERT INTO sb_user (department_id, first, last) VALUES (4, 'Benita', 'Brand');