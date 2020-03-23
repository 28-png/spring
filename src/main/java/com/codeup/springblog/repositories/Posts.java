package com.codeup.springblog.repositories;

import com.codeup.springblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Posts extends JpaRepository<Post, Long> {
Post findPostById(long id);
Posts getPostByTitle(String title);
Posts deleteById(long id);

}
