package com.codeup.springblog;

import com.codeup.springblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Posts extends JpaRepository<Post, Long> {
}
