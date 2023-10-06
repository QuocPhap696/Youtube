package com.example.youtube.repository;

import com.example.youtube.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsernameIgnoreCaseOrEmailIgnoreCaseOrPhone(String username, String email, String phone);
    boolean existsByUsernameIgnoreCase(String username);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByPhone(String phone);
    @Query("SELECT t.title, COUNT(*) FROM User u " +
            "LEFT JOIN View v ON u.id = v.user.id " +
            "LEFT JOIN Video vi ON v.video.id = vi.id " +
            "LEFT JOIN TagDetail td ON vi.id = td.video.id " +
            "LEFT JOIN Tag t ON td.tag.id = t.id " +
            "WHERE u.id = :userId " +
            "GROUP BY t.title " +
            "ORDER BY COUNT(*) DESC " +
            "LIMIT 3")
    List<Object[]> findTopTwoTagsByUserId(int userId);
    User findByUsername(String username);
    User findById(int id);

}
