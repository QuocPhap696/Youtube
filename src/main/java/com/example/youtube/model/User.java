package com.example.youtube.model;

import com.example.youtube.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Video> videos;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Likes> likes;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<View> views;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Comment> comments;


}
