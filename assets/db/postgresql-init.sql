CREATE DATABASE ref_letters_db;
\c ref_letters_db

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
                                           PRIMARY KEY (id)
);

--
-- Initial values for testing
--
INSERT INTO reference_letter_requests VALUES
                                          (1,1,1,'carrier1','carrier1@gmail.com'),
                                          (2,2,2,'carrier2','carrier2@gmail.com'),
                                          (3,3,3,'carrier3','carrier3@gmail.com'),
                                          (4,4,4,'carrier4','carrier4@gmail.com'),
                                          (5,5,5,'carrier5','carrier5@gmail.com');

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS teachers;

CREATE TABLE teachers (
                          id int NOT NULL,
                          full_name varchar(45) NOT NULL,
                          email varchar(45) DEFAULT NULL,
                          courses_id int DEFAULT NULL,
                          certificates_id int NOT NULL,
                          PRIMARY KEY (id)
);

--
-- Initial values for testing
--
INSERT INTO teachers VALUES
                         (1,'tsadimas','tsadimas@hua.gr',1,1),
                         (2,'varlamis','varlamis@hua.gr',2,2),
                         (3,'diou','cdiou@hua.gr',3,3),
                         (4,'cleobar','cleobar@hua.gr',4,4),
                         (5,'violos','violos@hua.gr',5,5);

--
-- Table structure for table `courses` and `certificates`
--

DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS certificates;

CREATE TABLE courses (
                         id int NOT NULL,
                         title varchar(45) DEFAULT NULL,
                         university varchar(45) DEFAULT NULL,
                         PRIMARY KEY (id)
);

CREATE TABLE certificates (
                              id int NOT NULL,
                              title varchar(45) DEFAULT NULL,
                              university varchar(45) DEFAULT NULL,
                              PRIMARY KEY (id)
);

--
-- Initial values for testing
--
INSERT INTO courses VALUES
                        (1,'devops','HUA'),
                        (2,'hci','HUA'),
                        (3,'investment valuation','HUA'),
                        (4,'javaII', 'HUA'),
                        (5,'distributed systems','HUA');

INSERT INTO certificates VALUES
                             (1,'devops-master','HUA'),
                             (2,'humanity','EKPA'),
                             (3,'investments','OPA'),
                             (4,'javaprogramming', 'HUA'),
                             (5,'data-mining','HUA');

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
                          PRIMARY KEY (id)
);

--
-- Initial values for testing
--
INSERT INTO students VALUES
                         (1,'Panagiotis Bellias','it21871','it21871@hua.gr','School of Digital Technology Harokopio University of Athens', 'www.google.com'),
                         (2,'Nikitas Thermos','it21828','it21828@hua.gr','School of Digital Technology Harokopio University of Athens', 'www.google.com'),
                         (3,'Efstratios Kouzeleas','it21846','it21846@hua.gr','School of Digital Technology Harokopio University of Athens', 'www.google.com');