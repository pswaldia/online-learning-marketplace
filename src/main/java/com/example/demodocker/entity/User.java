package com.example.demodocker.entity;

import com.example.demodocker.enums.Experience;
import com.example.demodocker.enums.Profession;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, unique = true, nullable = false)
    private String email;

    @Column(name = "first_name", length = 45, nullable = false)
    private String firstName;

    @Column(name="last_name", length = 45, nullable = false)
    private String lastName;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(length = 64)
    private String image;

    private boolean enabled;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Experience experience;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Profession profession;

    @ManyToMany
    @JoinTable(name="users_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();

    // unidirectional many to many mapping
    @ManyToMany
    @JoinTable(name="users_interest", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name="interest_id"))
    private Set<Interest> interests = new HashSet<>();

    public User(String email, String firstName, String lastName, String password, String bio, Experience experience, Profession profession) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.bio = bio;
        this.experience = experience;
        this.profession = profession;
    }

    public User(Long id, String email, String firstName, String lastName, String password, String bio,Experience experience, Profession profession ) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.experience = experience;
        this.profession = profession;
        this.bio = bio;
    }

    @Transient
    public String getPhotosImagePath(){
        if(id == null || image == null)
            return "/images/default-img.png";
        return "/user-photos/"+this.id+"/"+this.image;
    }
}
