CREATE DATABASE ref_letters_db;
\c ref_letters_db

--
-- Table structure for table `users`
--
DROP TABLE IF EXISTS users;

CREATE TABLE users ( -- possible issue
    username varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
    enabled smallint NOT NULL, -- possible issue
    PRIMARY KEY (username)
);

--
-- Initial values for testing
--
INSERT INTO users VALUES
   ('panagiotis', '$2a$12$jdOzN2OEzGZTxR1fEj0LReWVdTsi0zkELNdvPcPOfBW1S.5MtUNlS', 1), --panagiotis123
   ('stratos', '$2a$12$5k82OEFceH680YjVK5mucO/hqnztgWjzm7qyc7jMtYnYxfkKhmIW.', 1), --stratos123
   ('nikitas', '$2a$12$IvOBE9OMSDHtcQIEbKNeou92ID3nVvknRmnqFUKrD4Z6RwdffMzI2', 1), --nikitas123
   ('root', '$2a$12$BcO9G1DrN2QEsgoMkppoXugAps3FFIvPPQ7MXd0L3C2.asJxZ/bsi', 1); --root123

--
-- Table structure for table `authorities`
--
CREATE TABLE IF NOT EXISTS authorities ( -- possible issue
    id int NOT NULL,
    username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

--
-- Initial values for testing
--
INSERT INTO authorities VALUES
    (1, 'root', 'ROLE_ADMIN'),
    (2, 'panagiotis', 'ROLE_TEACHER'),
    (3, 'stratos', 'ROLE_STUDENT'),
    (4, 'nikitas', 'ROLE_STUDENT');