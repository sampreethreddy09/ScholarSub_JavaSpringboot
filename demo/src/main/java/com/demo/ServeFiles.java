package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ServeFiles implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ServeFiles.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/plagreport/**")
                .addResourceLocations("file:Uploaded_Files/");
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:controllers/Uploaded_Files/");
    }

    @GetMapping("/plagreport/{filename}")
    public ResponseEntity<Resource> servePlagiarismReport(@PathVariable String filename) throws MalformedURLException, IOException {
        Path filePath = Paths.get("Uploaded_Files", filename);
        Resource resource = new UrlResource(filePath.toUri());

        MediaType contentType = MediaType.TEXT_PLAIN;
        contentType = MediaTypeFactory.getMediaType(resource).orElse(MediaType.TEXT_PLAIN);

        return ResponseEntity.ok()
                .contentType(contentType)
                .body(resource);
    }

    @GetMapping("/files/{a_id}/{filename}")
    public ResponseEntity<Resource> serveFiles(@PathVariable String a_id, @PathVariable String filename) throws MalformedURLException {
        Path filePath = Paths.get("controllers", "Uploaded_Files", a_id, filename);
        Resource resource = new UrlResource(filePath.toUri());

        MediaType contentType = MediaType.TEXT_PLAIN;
        contentType = MediaTypeFactory.getMediaType(resource).orElse(MediaType.TEXT_PLAIN);

        return ResponseEntity.ok()
                .contentType(contentType)
                .body(resource);
    }

    @GetMapping("/file-content/{a_id}/{filename}")
    public ResponseEntity<String> getFileContent(@PathVariable String a_id, @PathVariable String filename) throws IOException {
        Path filePath = Paths.get("Uploaded_Files",a_id , filename);
        Resource resource = new UrlResource(filePath.toUri());

        // Read the content of the code file
        String content = new String(resource.getInputStream().readAllBytes());

        return ResponseEntity.ok()
                .body(content);
    }
}