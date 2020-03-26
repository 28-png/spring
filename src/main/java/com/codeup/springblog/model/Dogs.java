package com.codeup.springblog.model;

import javax.persistence.*;

@Entity
@Table(name = "dogs")
public class Dogs {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "INT(11) UNSIGNED")
     private long id;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String group;
}
