package com.health.healthcare.rest;

import com.health.healthcare.model.UserDataDTO;
import com.health.healthcare.service.UserDataService;
import jakarta.validation.Valid;
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
@RequestMapping(value = "/api/userDatas", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserDataResource {

    private final UserDataService userDataService;

    public UserDataResource(final UserDataService userDataService) {
        this.userDataService = userDataService;
    }
    
    @GetMapping("/")
    public ResponseEntity<List<UserDataDTO>> getAllUserDatas() {
        return ResponseEntity.ok(userDataService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDataDTO> getUserData(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(userDataService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createUserData(@RequestBody @Valid final UserDataDTO userDataDTO) {
        final Long createdId = userDataService.create(userDataDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUserData(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final UserDataDTO userDataDTO) {
        userDataService.update(id, userDataDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserData(@PathVariable(name = "id") final Long id) {
        userDataService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
