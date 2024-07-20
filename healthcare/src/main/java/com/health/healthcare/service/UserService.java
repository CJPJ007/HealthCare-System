package com.health.healthcare.service;

import com.health.healthcare.domain.User;
import com.health.healthcare.domain.UserData;
import com.health.healthcare.model.UserDTO;
import com.health.healthcare.repos.UserDataRepository;
import com.health.healthcare.repos.UserRepository;
import com.health.healthcare.util.NotFoundException;
import com.health.healthcare.util.ReferencedWarning;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(final UserRepository userRepository,
            final UserDataRepository userDataRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDataRepository = userDataRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public UserDTO create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return mapToDTO(userRepository.save(user), userDTO);
    }

    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAge(user.getAge());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setUpdateDate(user.getUpdateDate());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setAge(user.getAge());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setCreatedDate(userDTO.getCreatedDate());
        user.setUpdateDate(userDTO.getUpdateDate());
        return user;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final UserData userUserData = userDataRepository.findFirstByUser(user);
        if (userUserData != null) {
            referencedWarning.setKey("user.userData.user.referenced");
            referencedWarning.addParam(userUserData.getId());
            return referencedWarning;
        }
        return null;
    }

    public UserDTO getUser(@Valid UserDTO userDTO) {
        log.info("getUser : {}", userDTO.toString());
        try {
            List<User> user = userRepository.findByEmail(userDTO.getEmail());
            if (user != null && !user.isEmpty() && user.get(0) != null) {
                return mapToDTO(user.get(0), userDTO);
            } else {
                log.error("User not found");
            }
        } catch (Exception e) {
            log.error("Exception in getUser", e);
        }
        return new UserDTO();
    }

}
