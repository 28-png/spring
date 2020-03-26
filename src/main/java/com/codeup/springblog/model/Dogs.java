package com.codeup.springblog.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dogs")
public class Dogs {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dog")
    private List<Breeds> breed;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "INT(11) UNSIGNED")
    private long id;
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String group;

    public Dogs(List<Breeds> breed, String group) {
        this.breed = breed;
        this.group = group;
    }

    public List<Breeds> getBreed() {
        return breed;
    }

    public void setBreed(List<Breeds> breed) {
        this.breed = breed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
