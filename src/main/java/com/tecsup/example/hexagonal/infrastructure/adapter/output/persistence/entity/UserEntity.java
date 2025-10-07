package com.tecsup.example.hexagonal.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 150)
    private String email;

    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "middle_name", nullable = false, length = 100)
    private String middleName;

    @Column(name = "dni", nullable = false, length = 8)
    private Integer dni;

    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Column(name = "age", nullable = false)
    private int age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;


}
