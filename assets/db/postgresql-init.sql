CREATE DATABASE ref_letters_db;
\c ref_letters_db



--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS teachers;

CREATE TABLE teachers (
  id int NOT NULL,
  full_name varchar(45) NOT NULL,
  email varchar(45) DEFAULT NULL,
  users_username varchar(50) NOT NULL,
  date_of_birth date NOT NULL DEFAULT CURRENT_DATE,
  PRIMARY KEY (id),
  CONSTRAINT fk_users FOREIGN KEY(users_username) REFERENCES users(username)
      ON DELETE NO ACTION ON UPDATE NO ACTION
);

--
-- Initial values for testing
--
INSERT INTO teachers VALUES
     (1,'panagiotis','it21871@hua.gr','panagiotis','2017-04-30');

--
-- Table structure for table `courses` and `certificates`
--

DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS certificates;

CREATE TABLE courses (
                         id int NOT NULL,
                         title varchar(45) DEFAULT NULL,
                         university varchar(45) DEFAULT NULL,
                         teacher_id int DEFAULT NULL,
                         PRIMARY KEY (id),
                         CONSTRAINT fk_teachers FOREIGN KEY(teacher_id) REFERENCES teachers(id)
                             ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE certificates (
                              id int NOT NULL,
                              title varchar(45) DEFAULT NULL,
                              university varchar(45) DEFAULT NULL,
                              teacher_id int DEFAULT NULL,
                              date_taken date NOT NULL DEFAULT CURRENT_DATE,
                              PRIMARY KEY (id),
                              CONSTRAINT fk_teachers FOREIGN KEY(teacher_id) REFERENCES teachers(id)
                                  ON DELETE NO ACTION ON UPDATE NO ACTION
);

--
-- Initial values for testing
--
INSERT INTO courses VALUES
    (1,'devops','HUA', 1);

INSERT INTO certificates VALUES
    (1,'devops-master','HUA', 1);

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS students;

CREATE TABLE students (
  id int NOT NULL,
  full_name varchar(45) NOT NULL,
  uni_id varchar(20) NOT NULL,
  email varchar(45) DEFAULT NULL,
  school varchar(90) DEFAULT NULL,
  url_grading_file varchar(45) DEFAULT NULL,
  users_username varchar(50) NOT NULL,
  date_of_birth date NOT NULL DEFAULT CURRENT_DATE,
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
   id int NOT NULL,
   id_teacher int NOT NULL,
   id_student int NOT NULL,
   carrier_name varchar(45) NOT NULL,
   carrier_email varchar(45) NOT NULL,
   last_update date NOT NULL DEFAULT CURRENT_DATE,
   PRIMARY KEY (id),
   CONSTRAINT fk_teacher FOREIGN KEY(id_teacher) REFERENCES teachers(id),
   CONSTRAINT fk_student FOREIGN KEY(id_student) REFERENCES students(id)
);


-- Initial values for testing
--
INSERT INTO reference_letter_requests VALUES
  (1,1,1,'carrier1','carrier1@gmail.com'),
  (2,1,2,'carrier2','carrier2@gmail.com');
