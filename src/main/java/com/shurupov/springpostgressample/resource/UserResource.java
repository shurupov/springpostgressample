package com.shurupov.springpostgressample.resource;

import com.shurupov.springpostgressample.dto.UserDTO;
import com.shurupov.springpostgressample.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * REST controller for managing user
 * @author Evgeny Shurupov
 */
@RestController
@RequestMapping("/api/users")
public class UserResource {

    private static final Logger log = LoggerFactory.getLogger(UserResource.class);

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findUsers(@RequestParam(required = false) Long departmentId,
                                                   @RequestParam(required = false) String first,
                                                   @RequestParam(required = false) String last){

        return ResponseEntity.ok(userService.findUsers(first, last, departmentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        log.debug("REST request to get User with id {}", id);
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> post(@RequestBody UserDTO userDTO) {
        log.debug("REST request to add User: {}", userDTO);

        UserDTO user = userService.create(userDTO);
        URI uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/{id}")
    ResponseEntity<UserDTO> put(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        log.debug("REST request to update User with id {}: {}", id, userDTO);
        UserDTO user = userService.edit(id, userDTO);
        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(user);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        log.debug("REST request to remove User with id {}", id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
