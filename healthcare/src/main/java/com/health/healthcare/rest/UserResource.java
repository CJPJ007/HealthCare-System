package com.health.healthcare.rest;

import com.health.healthcare.model.UserDTO;
import com.health.healthcare.service.UserService;
import com.health.healthcare.util.ReferencedException;
import com.health.healthcare.util.ReferencedWarning;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserResource {

    private final UserService userService;

    public UserResource(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid final UserDTO userDTO) throws InterruptedException {
        final UserDTO createdUser = userService.create(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/getUser")
    public ResponseEntity<UserDTO> getUser(@RequestBody @Valid final UserDTO userDTO) {
        log.info("Inside getUser");
        return ResponseEntity.ok(userService.getUser(userDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUser(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final UserDTO userDTO) {
        userService.update(id, userDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") final Long id) {
        final ReferencedWarning referencedWarning = userService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
