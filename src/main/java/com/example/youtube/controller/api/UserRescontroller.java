package com.example.youtube.controller.api;

import com.example.youtube.Service.AuthService;
import com.example.youtube.Service.UserService;
import com.example.youtube.Service.user.request.RegisterSaveRequest;
import com.example.youtube.model.User;

import com.example.youtube.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
    @RequestMapping("/api/users")
public class UserRescontroller {
        private final AuthService authService;
        private final UserService userService ;
        private final UserRepository userRepository;
        @Autowired
        private ResourceLoader resourceLoader;
    public UserRescontroller(AuthService authService, UserService userService1, UserRepository userRepository) {
        this.authService = authService;
        this.userService = userService1;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid RegisterSaveRequest request){

        authService.create(request);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getUserLog")
    public ResponseEntity<?> getUserLog(){
        User user = authService.findByName(authService.getCurrentUser());
            return ResponseEntity.ok(user);
    }
    @PostMapping("/uploadAvt")
    @ResponseBody
    public ResponseEntity<?> handleAvatarUpload(@ModelAttribute com.example.youtube.Service.user.request.RegisterSaveRequest registerSaveRequest) {

        MultipartFile avtFile = registerSaveRequest.getImg();
        if ( avtFile.isEmpty()) {
            return ResponseEntity.badRequest().body("Video and image files are required.");
        }

        try {
            Resource resource = resourceLoader.getResource("classpath:/src/main/resources/static/assets/images/avatar/");
            String templatePath = resource.getFile().getAbsolutePath();
            Path p = Paths.get(templatePath + File.separator + avtFile.getOriginalFilename());
            InputStream inputStream1 = avtFile.getInputStream();
            Files.copy(inputStream1, p, StandardCopyOption.REPLACE_EXISTING);
            User user = authService.findByName(authService.getCurrentUser());
            user.setAvatar(p.toString().split("assets")[1]);
            userRepository.save(user);
            return ResponseEntity.ok("avatar uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while uploading the avatar.");
        }

    }

}


