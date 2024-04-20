package com.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class PlagiarismController {

    @GetMapping("/run-plagiarism-test/{assignmentId}")
    public ResponseEntity<String> runPlagiarismTest(@PathVariable int assignmentId) {
        String targetDirectory = "C:/Users/Uday Kiran Reddy N/Documents/WebDev/ScholarSub_Java/uploaded_files/" + assignmentId;

        try {
            // Change directory to the target directory
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "cd /d " + targetDirectory + " && dir /b");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Read the output of the command
            List<String> fileNames = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    fileNames.add(line.trim());
                }
            }

            // Process file names
            if (!fileNames.isEmpty()) {
                // Choose language based on file extension
                String firstFileName = fileNames.get(0);
                String[] fileNameParts = firstFileName.split("\\.");
                String fileExtension = fileNameParts.length > 1 ? fileNameParts[fileNameParts.length - 1].toLowerCase() : null;

                Map<String, String> languageMap = new HashMap<>();
                languageMap.put("txt", "Plain Text");
                languageMap.put("java", "java");
                languageMap.put("c", "c");
                languageMap.put("cpp", "c++");
                languageMap.put("py", "python");
                // languageMap.put("java", "java");
                // languageMap.put("java", "java");
                // Add more mappings as needed

                String language = languageMap.getOrDefault(fileExtension, "Unknown Language");

                System.out.println("Running plagiarism test for " + fileNames + " files in " + language + " language.");

                // Execute the command
                String command = "dolos run -l " + language + " " + String.join(" ", fileNames);

                System.out.println("Running command: " + command);

                Process process2 = Runtime.getRuntime().exec(command, null, new File(targetDirectory));
                process2.waitFor();

                // Process the output if needed
                BufferedReader outputReader = new BufferedReader(new InputStreamReader(process2.getInputStream()));
                List<String> outputLines = new ArrayList<>();
                while ((line = outputReader.readLine()) != null) {
                    outputLines.add(line);
                }

                // Save to CSV file
                saveToCsv(outputLines, "output.csv");

                // Return success response
                return ResponseEntity.ok().body("Plagiarism test completed successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No files found in the directory.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while running the plagiarism test.");
        }
    }

    private void saveToCsv(List<String> lines, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error
        }
    }
}
