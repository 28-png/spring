package com.codeup.springblog.controllers;

import com.codeup.springblog.model.User;
import com.codeup.springblog.services.EmailClass;
import com.codeup.springblog.repositories.Posts;
import com.codeup.springblog.model.Post;
import com.codeup.springblog.repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class PostController {
    private final Posts postsDao;
    private final UserRepo usersDao;
    private final EmailClass emailService;
    public PostController(Posts postsDao, UserRepo usersDao, EmailClass emailService) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }




    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

   @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model, Principal principal) {
       String userName = "";
       if (principal != null) {
           userName = principal.getName();
       }
       model.addAttribute("userName", userName);
       model.addAttribute("post",postsDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreatePostForm(){
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (loggedIn != null)
            return "posts/create";
        else
            return "redirect:/login";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post newPost, @RequestParam String title, @RequestParam String body ){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newPost.setTitle(title);
        newPost.setBody(body);
        newPost.setUser(loggedInUser);
        postsDao.save(newPost);
        emailService.prepareAndSend(newPost, title, body);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedInUser.getId() == postsDao.getOne(id).getUser().getId())
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post postToEdit = postsDao.getOne(id);
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedInUser.getId() == postsDao.getOne(id).getUser().getId())
        model.addAttribute("post", postToEdit);
        return "edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @RequestParam String title ,@RequestParam String body) {

        Post p = postsDao.getOne(id);
        p.setTitle(title);
        p.setBody(body);
        postsDao.save(p);
        return "redirect:/posts";
    }

}