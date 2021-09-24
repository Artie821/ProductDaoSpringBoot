package com.example.demo.shop.models;

import com.example.demo.shop.security.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 6, max = 50)
    @Column(nullable = false, unique = true, length = 60)
    private String username;
    @Size(min = 3, max = 80)
    @Column(nullable = false, unique = true, length = 80)
    private String password;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Role> roles;
    @Email
    @Size(min = 3, max = 50)
    @Column(nullable = false, unique = true, length = 80)
    private String email;

    private boolean active;

}
