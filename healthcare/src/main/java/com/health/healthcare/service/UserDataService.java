package com.health.healthcare.service;

import com.health.healthcare.domain.User;
import com.health.healthcare.domain.UserData;
import com.health.healthcare.model.UserDataDTO;
import com.health.healthcare.repos.UserDataRepository;
import com.health.healthcare.repos.UserRepository;
import com.health.healthcare.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserDataService {

    private final UserDataRepository userDataRepository;
    private final UserRepository userRepository;

    public UserDataService(final UserDataRepository userDataRepository,
            final UserRepository userRepository) {
        this.userDataRepository = userDataRepository;
        this.userRepository = userRepository;
    }

    public List<UserDataDTO> findAll() {
        final List<UserData> userDatas = userDataRepository.findAll(Sort.by("id"));
        return userDatas.stream()
                .map(userData -> mapToDTO(userData, new UserDataDTO()))
                .toList();
    }

    public UserDataDTO get(final Long id) {
        return userDataRepository.findById(id)
                .map(userData -> mapToDTO(userData, new UserDataDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UserDataDTO userDataDTO) {
        final UserData userData = new UserData();
        mapToEntity(userDataDTO, userData);
        return userDataRepository.save(userData).getId();
    }

    public void update(final Long id, final UserDataDTO userDataDTO) {
        final UserData userData = userDataRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDataDTO, userData);
        userDataRepository.save(userData);
    }

    public void delete(final Long id) {
        userDataRepository.deleteById(id);
    }

    private UserDataDTO mapToDTO(final UserData userData, final UserDataDTO userDataDTO) {
        userDataDTO.setId(userData.getId());
        userDataDTO.setSleep(userData.getSleep());
        userDataDTO.setWater(userData.getWater());
        userDataDTO.setSteps(userData.getSteps());
        userDataDTO.setCreateDate(userData.getCreateDate());
        userDataDTO.setUser(userData.getUser() == null ? null : userData.getUser().getId());
        return userDataDTO;
    }

    private UserData mapToEntity(final UserDataDTO userDataDTO, final UserData userData) {
        userData.setSleep(userDataDTO.getSleep());
        userData.setWater(userDataDTO.getWater());
        userData.setSteps(userDataDTO.getSteps());
        userData.setCreateDate(userDataDTO.getCreateDate());
        final User user = userDataDTO.getUser() == null ? null : userRepository.findById(userDataDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        userData.setUser(user);
        return userData;
    }

}
