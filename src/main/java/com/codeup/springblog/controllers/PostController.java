package com.codeup.springblog.controllers;

import com.codeup.springblog.Posts;
import com.codeup.springblog.model.Post;
import com.codeup.springblog.model.User;
import com.codeup.springblog.model.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final Posts postsDao;
    private final UserRepo usersDao;
    public PostController(Posts postsDao, UserRepo usersDao) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
    }




    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts";
    }

    @GetMapping("/posts/{id}/show")
    @ResponseBody
    public String getPost(@PathVariable int id, @RequestParam String email, Model model) {
        model.addAttribute("email", usersDao.findByEmail(email));
        return "view an individual post, id="+id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String getCreatePostForm(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "create a new post";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id){
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @RequestParam String title ,@RequestParam String body) {
        Post p = postsDao.getOne(id);
        User u = usersDao.getOne(1L);
        u.setId(1L);
        p.setTitle(title);
        p.setBody(body);
        postsDao.save(p);
        return "redirect:/posts/{id}/show";
    }



}