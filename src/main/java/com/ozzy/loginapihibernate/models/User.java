package com.ozzy.loginapihibernate.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="users")
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="firstname")
    @NonNull
    private String firstname;
    
    @Column(name="lastname")
    @NonNull
    private String lastname;
    
    @Column(name="username")
    @NonNull
    private String username;
    
    @Column(name="email")
    @NonNull
    private String email;
    
    @Column(name="password")
    @NonNull
    private String password;
    
}
