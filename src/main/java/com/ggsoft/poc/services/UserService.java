package com.ggsoft.poc.services;

import com.ggsoft.poc.domain.User;
import com.ggsoft.poc.repos.GroupRepository;
import com.ggsoft.poc.repos.UserRepository;
import com.ggsoft.poc.services.dto.GroupDTO;
import com.ggsoft.poc.services.dto.UserDTO;
import com.ggsoft.poc.services.dto.UserWithoutMembershipsDTO;
import com.ggsoft.poc.services.mapper.GroupMapper;
import com.ggsoft.poc.services.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Victor Gil on 3/26/2017.
 */

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @PostConstruct
    public void init() {
        logger.info("Initializing UserService");
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUser(String id) {
        return userRepository.findById(id)
                .map(userMapper::toUserDTO);
    }

    public UserDTO createUser(UserDTO dto) {
        User user = userMapper.fromUserDTO(dto);
        user.setId(UUID.randomUUID().toString());
        user = userRepository.save(user);
        return userMapper.toUserDTO(user);
    }

    public UserWithoutMembershipsDTO updateUser(UserWithoutMembershipsDTO dto) {
        return userRepository.findById(dto.getId())
                .map(user -> {
                    userMapper.updateUserInformation(dto, user);
                    return user;
                })
                .map(userMapper::toUserWithoutMembershipsDto)
                .orElseThrow(UserDoesNotExist::new);
    }

    public void removeUser(String id) {
        userRepository.delete(id);
    }

    public List<GroupDTO> getMemberships(UserWithoutMembershipsDTO dto) {
        return groupRepository
                .findByMembersContaining(userMapper.fromUserDtoWithoutMemberships(dto))
                .stream()
                .map(groupMapper::toGroupDto)
                .collect(Collectors.toList());

    }

}
