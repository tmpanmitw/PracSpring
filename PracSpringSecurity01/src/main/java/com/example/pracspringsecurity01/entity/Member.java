package com.example.pracspringsecurity01.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role; // ROLE_USER, ROLE_ADMIN
    // 권한세팅 query
    // update member set role = 'ROLE_MANAGER' where id = 2;
    // update member set role = 'ROLE_ADMIN' where id = 3;
    //자동으로 createDate만들어준다.
    @CreationTimestamp
    private Timestamp createDate;

}
