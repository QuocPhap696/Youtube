package com.example.youtube.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "userSub_id")
    private User userSub;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Subscribe(User userSub, User user) {
        this.userSub = userSub;
        this.user = user;
    }
}
