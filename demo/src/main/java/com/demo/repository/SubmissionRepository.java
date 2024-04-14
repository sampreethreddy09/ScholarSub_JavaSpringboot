package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    // You can define custom query methods here if needed
    List<Submission> findByStudentId(String studentId);

    List<Submission> findByAssignmentId(int assignmentId);

    // Define custom query to fetch submissions with obtained marks and feedback
    @Query("SELECT s, r.obtainedMarks, r.feedback, st FROM Submission s LEFT JOIN Result r ON s.id = r.submission.id JOIN Student st ON s.student.id = st.id WHERE s.assignment.id = ?1")
    List<Object[]> fetchSubmissionsWithMarksAndFeedback(int assignmentId);

    // Define custom query to count submissions for a specific assignment
    @Query("SELECT COUNT(s) FROM Submission s WHERE s.assignment.id = ?1")
    Integer countSubmissionsByAssignmentId(int assignmentId);
}