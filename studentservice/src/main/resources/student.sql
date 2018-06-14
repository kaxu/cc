CREATE TABLE cc_class_info
(
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(50),
    create_time datetime NOT NULL,
    version INT NOT NULL
);

CREATE TABLE cc_student_info
(
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(50),
    cc_class_info_id bigint NOT NULL,
    create_time timestamp NOT NULL,
    version INT DEFAULT 0 NOT NULL
);

INSERT INTO kaxudb.cc_class_info (name, create_time, version) VALUES ( 'class1', now(), 0);
INSERT INTO kaxudb.cc_class_info (name, create_time, version) VALUES ('class2', now(), 0);
INSERT INTO kaxudb.cc_class_info ( name, create_time, version) VALUES ('class3', now() , 0);

INSERT INTO kaxudb.cc_student_info (id, name, cc_class_info_id, create_time, version) VALUES (1, 'kaxu', 1, '2018-06-14 13:10:27', 0);
INSERT INTO kaxudb.cc_student_info (id, name, cc_class_info_id, create_time, version) VALUES (2, 'dodo', 1, '2018-06-14 13:10:39', 0);
INSERT INTO kaxudb.cc_student_info (id, name, cc_class_info_id, create_time, version) VALUES (3, 'vovo', 1, '2018-06-14 13:10:45', 0);
INSERT INTO kaxudb.cc_student_info (id, name, cc_class_info_id, create_time, version) VALUES (4, 'aaron', 2, '2018-06-14 13:10:56', 0);
INSERT INTO kaxudb.cc_student_info (id, name, cc_class_info_id, create_time, version) VALUES (5, 'bobo', 2, '2018-06-14 13:11:03', 0);