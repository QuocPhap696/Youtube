package com.example.youtube.Service.user.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSaveRequest {
    private String id;
    @NotBlank(message = "trống")

    private String username;
    @NotBlank(message = "ko dc đe trong")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,15}$",message = "password không đúng yêu cầu")
    private String password;
    @NotBlank(message = "ko dc đe trong")
    private String email;
    @NotBlank(message = "ko dc đe trong")
    @Pattern(regexp = "^[0][0-9]{9}$",message = "số điện thoại phải gồm 10 chữ số và bắt đầu bằng số 0")
    private String phone;
    @NotBlank(message = "ko dc đe trong")
    private String address;
    private MultipartFile img;
}
