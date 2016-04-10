--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/migrations.xml
--  Ran at: 10/04/16 21:00
--  Against: root@localhost@jdbc:mysql://localhost:3306/bookmarks
--  Liquibase version: 3.4.2
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = '192.168.99.1 (192.168.99.1)', LOCKGRANTED = '2016-04-10 21:00:42.947' WHERE ID = 1 AND LOCKED = 0;

--  Changeset src/main/resources/migrations.xml::2::matt
CREATE TABLE bookmarks (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(255) NOT NULL, url VARCHAR(1024) NOT NULL, description VARCHAR(2048) NULL, user_id BIGINT NOT NULL, CONSTRAINT PK_BOOKMARKS PRIMARY KEY (id), CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('2', 'matt', 'src/main/resources/migrations.xml', NOW(), 2, '7:7456de68e3f83bb7ee082cd85fbc55d3', 'createTable', '', 'EXECUTED', NULL, NULL, '3.4.2');

--  Changeset src/main/resources/migrations.xml::3::matt
INSERT INTO users (id, username, password) VALUES ('1', 'matt', 'matt');

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('3', 'matt', 'src/main/resources/migrations.xml', NOW(), 3, '7:01ae7baf8f1eba4f2973347e9ccf90ee', 'insert', '', 'EXECUTED', NULL, NULL, '3.4.2');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

