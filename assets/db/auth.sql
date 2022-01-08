\c ref_letters_db

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS users ( -- possible issue
    username varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
    enabled smallint NOT NULL, -- possible issue
    PRIMARY KEY (username)
);

--
-- Initial values for testing
--

INSERT INTO users VALUES
   ('panagiotis', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', 1),
   ('stratos', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', 1),
   ('nikitas', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', 1),
   ('argiris', '$2a$04$DR/f..s1siWJc8Xg3eJgpeB28a4V6kYpnkMPeOuq4rLQ42mJUYFGC', 1),
   ('root', '$2a$12$bWiD/oOdJKADNJlJu0O96u9mQOFHj3n3Cw94CRTayOGer8GEflkeW', 1);

--
-- Table structure for table `authorities`
--

CREATE TABLE IF NOT EXISTS authorities ( -- possible issue
    username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,
    UNIQUE (username,authority),
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

--
-- Initial values for testing
--
INSERT INTO authorities VALUES
    ('root', 'ROLE_ADMIN'),
    ('panagiotis', 'ROLE_STUDENT'),
    ('stratos', 'ROLE_STUDENT'),
    ('nikitas', 'ROLE_STUDENT'),
    ('argiris', 'ROLE_TEACHER');