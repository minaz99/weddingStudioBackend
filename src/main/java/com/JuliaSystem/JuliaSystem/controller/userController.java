package com.JuliaSystem.JuliaSystem.controller;


import com.JuliaSystem.JuliaSystem.user.Role;
import com.JuliaSystem.JuliaSystem.user.User;
import com.JuliaSystem.JuliaSystem.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@AllArgsConstructor
public class userController {

    private final UserService userService;
    //@Autowired
   // public homeController(UserService userService) {this.userService = userService;}

    @GetMapping
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Hello! from a secured endpoint");
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @DeleteMapping(path = "/user/{userID}")
    public void deleteUser(@PathVariable("userID") Integer userID) {
        userService.deleteUser(userID);
    }
    @PutMapping(path = "/user/{userID}")
    public void updateUser(@PathVariable("userID") Integer userID, @RequestParam(required = false) String email, @RequestParam(required = false) String password, @RequestParam(required = false) Role role) {
        userService.updateUser(userID,email,password,role);
    }

}
