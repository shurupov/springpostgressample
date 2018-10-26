package com.shurupov.springpostgressample.controller;

import com.shurupov.springpostgressample.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<User>> findUsers(@RequestParam(required = false) String departmentId,
                                                @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName){

        return ResponseEntity.ok(Arrays.asList(
            new User(1L, "First"),
            new User(2L, "Second"),
            new User(3L, "Third")
        ));
    }

}
