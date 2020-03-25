package com.codeup.springblog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(nullable = false, columnDefinition = "INT(11) UNSIGNED")
    private long id;
@Column(nullable = false, length = 200, unique = true)
    private String title;
//@Column(nullable = false, name = "post_date", columnDefinition = "DATE")
//@DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date date;
@Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String body;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Post() {}

    public Post(String title, String body, User user) {
        this.title = title;
//        this.date = date;
        this.body = body;
        this.user = user;
    }
}
