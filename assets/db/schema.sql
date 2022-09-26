CREATE DATABASE ref_letters_db;
\c ref_letters_db

--
-- Table structure for table `users`
--
DROP TABLE IF EXISTS users;

CREATE TABLE users (
   username varchar(50) NOT NULL,
   password varchar(100) NOT NULL,
   enabled smallint NOT NULL,
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
    username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,
    UNIQUE (username, authority),
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

--
-- Initial values for testing
--
INSERT INTO authorities VALUES
    ('root', 'ROLE_ADMIN'),
    ('panagiotis', 'ROLE_TEACHER'),
    ('stratos', 'ROLE_STUDENT'),
    ('nikitas', 'ROLE_STUDENT');

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS teachers;

CREATE TABLE teachers (
      id SERIAL,
      full_name varchar(45) NOT NULL,
      email varchar(45) DEFAULT NULL,
      description varchar(255) NOT NULL,
      users_username varchar(50) NOT NULL,
      PRIMARY KEY (id),
      CONSTRAINT fk_users FOREIGN KEY(users_username) REFERENCES users(username)
          ON DELETE NO ACTION ON UPDATE NO ACTION
);

--
-- Initial values for testing
--
INSERT INTO teachers VALUES
    (1,'panagiotis','it21871@hua.gr','test-description','panagiotis');

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS students;

CREATE TABLE students (
      id SERIAL,
      full_name varchar(45) NOT NULL,
      uni_id varchar(20) NOT NULL,
      email varchar(45) DEFAULT NULL,
      school varchar(90) DEFAULT NULL,
      url_grading_file varchar(45) DEFAULT NULL,
      users_username varchar(50) NOT NULL,
      PRIMARY KEY (id),
      CONSTRAINT fk_users FOREIGN KEY(users_username) REFERENCES users(username)
          ON DELETE NO ACTION ON UPDATE NO ACTION
);

--
-- Initial values for testing
--
INSERT INTO students VALUES
     (1,'Nikitas Thermos','it21828','it21828@hua.gr','School of Digital Technology Harokopio University of Athens', 'www.google.com', 'nikitas'),
     (2,'Efstratios Kouzeleas','it21846','it21846@hua.gr','School of Digital Technology Harokopio University of Athens',
      'www.google.com', 'stratos');

--
-- Table structure for table `reference_letter_requests`
--

DROP TABLE IF EXISTS reference_letter_requests;

CREATE TABLE reference_letter_requests (
       id  SERIAL,
       id_teacher int NOT NULL,
       id_student int NOT NULL,
       carrier_name varchar(45) NOT NULL,
       carrier_email varchar(45) NOT NULL,
       is_approved bool DEFAULT FALSE,
       is_declined bool DEFAULT FALSE,
       is_pending bool DEFAULT TRUE,
       reference_letter_text varchar(512) NULL,
       PRIMARY KEY (id),
       CONSTRAINT fk_teacher FOREIGN KEY(id_teacher) REFERENCES teachers(id) ON DELETE CASCADE ON UPDATE CASCADE,
       CONSTRAINT fk_student FOREIGN KEY(id_student) REFERENCES students(id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Initial values for testing
--
INSERT INTO reference_letter_requests VALUES
      (1,1,1,'carrier1','carrier1@gmail.com', FALSE, FALSE, TRUE, ''),
      (2,1,2,'carrier2','carrier2@gmail.com', FALSE, FALSE, TRUE, '');
