package com.example.demodocker.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true,length = 128)
    private String authorName;

    @Column(nullable = true, length = 64)
    private String authorImage;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String authorDescription;

    public Author(String authorName, String authorDescription) {
        this.authorName = authorName;
        this.authorDescription = authorDescription;
    }
}
