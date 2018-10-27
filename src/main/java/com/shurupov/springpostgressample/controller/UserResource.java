package com.shurupov.springpostgressample.controller;

import com.shurupov.springpostgressample.domain.user.User;
import com.shurupov.springpostgressample.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findUsers(@RequestParam(required = false) String departmentId,
                                                @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName){

        return ResponseEntity.ok(userService.findUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userService.findById(id).map(ResponseEntity::ok).orElseThrow(() -> new EntityNotFoundException("User is not found"));
    }

}
