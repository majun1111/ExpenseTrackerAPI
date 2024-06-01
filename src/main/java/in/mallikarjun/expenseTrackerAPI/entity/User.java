package in.mallikarjun.expenseTrackerAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private long userId;

    private String name;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    private Long age;

    @Column(name="created_at",nullable=false,updatable= false)
    @CreationTimestamp
    private Timestamp createAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    private Timestamp updatedAt;

}
