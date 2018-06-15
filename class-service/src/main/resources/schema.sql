DROP TABLE IF EXISTS cc_class_info;

CREATE TABLE cc_class_info
(
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(50),
    create_time datetime NOT NULL,
    version INT NOT NULL
);

INSERT INTO kaxudb.cc_class_info (name, create_time, version) VALUES ( 'class1', now(), 0);
INSERT INTO kaxudb.cc_class_info (name, create_time, version) VALUES ('class2', now(), 0);
INSERT INTO kaxudb.cc_class_info ( name, create_time, version) VALUES ('class3', now() , 0);