// package com.demo.service;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mail.MailException;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import com.demo.model.Assignment;
// import com.demo.model.Section;
// import com.demo.model.Student;

// @Service
// public class EmailService {

//     @Autowired
//     private JavaMailSender emailSender;

//     // Method to send a simple email
//     public void sendSimpleEmail(String to, String subject, String text) {
//         SimpleMailMessage message = new SimpleMailMessage();
//         message.setTo(to);
//         message.setSubject(subject);
//         message.setText(text);

//         try {
//             emailSender.send(message);
//             System.out.println("Email sent successfully.");
//         } catch (MailException e) {
//             System.err.println("Error sending email: " + e.getMessage());
//         }
//     }

//     // Scheduled task to send reminder emails
//     @Scheduled(fixedRate = 1000 * 60 * 60) // Run every hour
//     public void sendReminderEmails() {
//         // Logic to find assignments due within the next 2 hours
//         // This is just a placeholder; implement your own logic
//         List<Assignment> assignmentsDueSoon = assignmentService.findAssignmentsDueSoon();

//         for (Assignment assignment : assignmentsDueSoon) {
//             // Logic to get students enrolled in the assignment
//             Section section = assignment.getSection();
//             List<Student> students = sectionStudentRepository.findStudentIdsBySectionId();

//             for (Student student : students) {
//                 String email = student.getEmail();
//                 String subject = "Reminder: Assignment Deadline Approaching";
//                 String text = "Hi " + student.getName() + ",\n\nThis is a reminder that the assignment \"" + assignment.getTitle() + "\" is due in 2 hours. Please submit your work on time.\n\nRegards,\nYour School";

//                 sendSimpleEmail(email, subject, text);
//             }
//         }
//     }
// }
