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