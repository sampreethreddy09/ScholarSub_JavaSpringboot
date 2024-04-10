CREATE Database IF NOT EXISTS `ScholarSub_Java`  ;

USE `ScholarSub_Java`;

-- table for students

CREATE TABLE `Student` (

  `s_id` varchar(13) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `semester` int NOT NULL,

  PRIMARY KEY (`s_id`)
 
);

-- table for teachers

CREATE TABLE `Teacher` (

  `t_id` varchar(13) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,

  PRIMARY KEY (`t_id`)

);

-- table for files

CREATE TABLE `File` (

  `f_id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `type` varchar(30) DEFAULT NULL,
  `size` INT DEFAULT NULL,
  `path` varchar(300) NOT NULL,

  PRIMARY KEY (`f_id`)

);


-- table for section

CREATE TABLE `Section` (
  `sec_id` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `capacity` INT NOT NULL,
  `t_id` varchar(13) NOT NULL,
  
  PRIMARY KEY (`sec_id`),
  KEY `fk_section_teacher_id` (`t_id`),

  CONSTRAINT `fk_section_teacher_id` FOREIGN KEY (`t_id`) REFERENCES `Teacher` (`t_id`) ON DELETE CASCADE

);



-- table for assignments

CREATE TABLE `Assignment` (
  `a_id` INT NOT NULL AUTO_INCREMENT ,
  `name` varchar(30) NOT NULL,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  `allow_late_submission` BOOLEAN NOT NULL,
  `schedule_right_now` BOOLEAN NOT NULL DEFAULT 1,
  `sec_id` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL, -- assignment description
  `constraints` varchar(255) DEFAULT NULL, --  could be on file type , size etc..
  `input_files_there` BOOLEAN DEFAULT 0,  -- if there is a assgn manual , or input files (0 - nothing , 1 - there are files);
  `is_evaluated` BOOLEAN DEFAULT 0, -- if the assignment is evaluated or not
  `max_marks` INT DEFAULT 10,

  PRIMARY KEY (`a_id`),
  KEY `fk_assignment_sec_id` (`sec_id`),

  CONSTRAINT `fk_assignment_sec_id` FOREIGN KEY (`sec_id`) REFERENCES `Section` (`sec_id`) ON DELETE CASCADE

);


-- table for assignment-files

CREATE TABLE `Assignment_Files`(

  `a_id` INT ,
  `f_id` INT,

  PRIMARY KEY (`a_id`,`f_id`),
  KEY `fk_assignment_files_a_id` (`a_id`),
  KEY `fk_assignment_files_f_id` (`f_id`),

  CONSTRAINT `fk_assignment_files_a_id` FOREIGN KEY (`a_id`) REFERENCES `Assignment` (`a_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_assignment_files_f_id` FOREIGN KEY (`f_id`) REFERENCES `File` (`f_id`) ON DELETE CASCADE

);

-- table for submisssions

CREATE TABLE `Submission` (

  `sub_id` INT NOT NULL AUTO_INCREMENT ,
  `tos` Datetime NOT NULL,
  `a_id` INT NOT NULL,
  `s_id` varchar(13) NOT NULL,

  PRIMARY KEY (`sub_id`),
  KEY `fk_submission_assignment_a_id` (`a_id`),
  KEY `fk_submission_student_s_id` (`s_id`),

  CONSTRAINT `fk_submission_assignment_a_id` FOREIGN KEY (`a_id`) REFERENCES `Assignment` (`a_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_submission_student_s_id` FOREIGN KEY (`s_id`) REFERENCES `Student` (`s_id`) ON DELETE CASCADE

);

-- table for submission-files

CREATE TABLE Submission_Files(

  `sub_id` INT,
  `f_id` INT NOT NULL,

  PRIMARY KEY (`sub_id` , `f_id`),
  KEY `fk_submission_files_sub_id` (`sub_id`),
  KEY `fk_submission_files_f_id` (`f_id`),

  CONSTRAINT `fk_submission_files_sub_id` FOREIGN KEY (`sub_id`) REFERENCES `Submission` (`sub_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_submission_files_f_id` FOREIGN KEY (`f_id`) REFERENCES `File` (`f_id`) ON DELETE CASCADE

);

-- table for results

CREATE TABLE `Result` (

  `sub_id` INT NOT NULL,
  `total_marks` INT NOT NULL,
  `obtained_marks` INT DEFAULT NULL,

  PRIMARY KEY (`sub_id`),
  KEY `fk_result_submission_id` (`sub_id`),
  
  CONSTRAINT `fk_result_submission_id` FOREIGN KEY (`sub_id`) REFERENCES `Submission` (`sub_id`) ON DELETE CASCADE

);

-- table for section-students

CREATE TABLE `Section_Student`(
  `sec_id` varchar(10) NOT NULL,
   `s_id` varchar(13) NOT NULL,

   PRIMARY KEY (`sec_id`,`s_id`),
   KEY `fk_section_student__sec_id` (`sec_id`),
   KEY `fk_section_student_s_id` (`s_id`),

   CONSTRAINT `fk_section_student__sec_id` FOREIGN KEY (`sec_id`) REFERENCES `Section` (`sec_id`) ON DELETE CASCADE,
   CONSTRAINT `fk_section_student_s_id` FOREIGN KEY (`s_id`) REFERENCES `Student` (`s_id`) ON DELETE CASCADE

);

insert into Student values ("PES1UG21CS681","Uday Kiran Reddy N","uday410ry@gmail.com","fxdxpx",5),
                            ("PES1UG21CS736","Y Uday Samreeth Reddy","uday410ry@gmail.com","fxdxqx",5),
                            ("PES1UG21CS684","Khushi","uday410ry@gmail.com","fxdxrx",5),
                            ("PES1UG21CS680","Aaradhya","uday410ry@gmail.com","fxdxsx",5),
                            ("PES1UG21CS700","Bachhan","uday410ry@gmail.com","fxdxtx",5),
                            ("PES1UG21CS235","Preethi","uday410ry@gmail.com","fxdxux",5),
                            ("PES1UG21CS420","Shravya","uday410ry@gmail.com","fxdxvx",5);

insert into Teacher values ("PES1PR21CS001","Alexa","alexa@gmail.com","mxdxpx"),
                            ("PES1AP21CS017","Siri","siri@gmail.com","mxdxqx"),
                            ("PES1JP21CS021","Cortana","cortana@gmail.com","mxdxrx"),
                            ("PES1JP21EC005","John","john@gmail.com","mxdxsx");

insert into Section values ("PES21CSDA1","DA Section-1",72,"PES1PR21CS001"),
                            ("PES21CSLMI","MI L Section",65,"PES1JP21CS021");

INSERT INTO Assignment (name, start_time, end_time, allow_late_submission, sec_id, description, constraints, input_files_there, is_evaluated, max_marks)
VALUES
  ("Assignment 1", "2024-04-01 00:00:00", "2024-04-05 23:59:59", 1, "PES21CSDA1", "Description 1", "Constraints 1", 0, 0, 10),
  ("Assignment 2", "2024-04-01 00:00:00", "2024-04-04 23:59:59", 0, "PES21CSDA1", "Description 2", "Constraints 2", 0, 0, 10),
  ("Assignment 3", "2024-04-01 00:00:00", "2024-04-03 23:59:59", 1, "PES21CSLMI", "Description 3", "Constraints 3", 1, 0, 10);


INSERT INTO Section_Student (sec_id, s_id)
VALUES
  ("PES21CSDA1", "PES1UG21CS235"),
  ("PES21CSDA1", "PES1UG21CS420"),
  ("PES21CSDA1", "PES1UG21CS680"),
  ("PES21CSDA1", "PES1UG21CS681"),
  ("PES21CSDA1", "PES1UG21CS684"),
  ("PES21CSDA1", "PES1UG21CS700"),
  ("PES21CSDA1", "PES1UG21CS736");

INSERT INTO Section_Student (sec_id, s_id)
VALUES
  ("PES21CSLMI", "PES1UG21CS235"),
  ("PES21CSLMI", "PES1UG21CS420"),
  ("PES21CSLMI", "PES1UG21CS680"),
  ("PES21CSLMI", "PES1UG21CS681"),
  ("PES21CSLMI", "PES1UG21CS684"),
  ("PES21CSLMI", "PES1UG21CS700"),
  ("PES21CSLMI", "PES1UG21CS736");


-- -- Procedure for listing SRN's of students who have not made a submission :

-- DELIMITER //

-- CREATE PROCEDURE ListStudentsWithoutSubmission(IN a_id_param INT)
-- BEGIN
--     SELECT DISTINCT S.s_id
--     FROM Student S
--     JOIN Section_Student SS ON S.s_id = SS.s_id
--     JOIN Section SE ON SS.sec_id = SE.sec_id
--     JOIN Assignment A ON SE.sec_id = A.sec_id AND A.a_id = a_id_param
--     LEFT JOIN Submission SB ON S.s_id = SB.s_id AND A.a_id = SB.a_id
--     WHERE SB.sub_id IS NULL;

-- END //

-- DELIMITER ;

-- -- Below is a stored procedure that takes an a_id as input and returns the maximum, minimum, average marks obtained, and the total marks for that assignment:

-- DELIMITER //

-- CREATE PROCEDURE GetAssignmentStats(IN a_id_param INT)
-- BEGIN
--     DECLARE max_mark INT;
--     DECLARE min_marks INT;
--     DECLARE avg_marks FLOAT;
--     DECLARE total_marks INT;

--     -- Get maximum marks
--     SELECT MAX(obtained_marks) INTO max_mark
--     FROM Result
--     WHERE sub_id IN (SELECT sub_id FROM Submission WHERE a_id = a_id_param);

--     -- Get minimum marks
--     SELECT MIN(obtained_marks) INTO min_marks
--     FROM Result
--     WHERE sub_id IN (SELECT sub_id FROM Submission WHERE a_id = a_id_param);

--     -- Get average marks
--     SELECT AVG(obtained_marks) INTO avg_marks
--     FROM Result
--     WHERE sub_id IN (SELECT sub_id FROM Submission WHERE a_id = a_id_param);

--     -- Get total marks
--     SELECT max_marks INTO total_marks
--     FROM assignment
--     WHERE a_id = a_id_param;

--     -- Return the results
--     SELECT max_mark, min_marks, avg_marks, total_marks;
-- END //

-- DELIMITER ;

-- -- Trigger for submission
-- DELIMITER //

-- CREATE TRIGGER onSubmission 
-- AFTER INSERT ON Submission
-- FOR EACH ROW
-- BEGIN
--   INSERT INTO Result VALUES (NEW.sub_id, 0, NULL);
-- END//

-- DELIMITER ;