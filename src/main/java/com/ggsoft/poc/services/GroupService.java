package com.ggsoft.poc.services;

import com.ggsoft.poc.domain.Group;
import com.ggsoft.poc.domain.User;
import com.ggsoft.poc.repos.GroupRepository;
import com.ggsoft.poc.repos.UserRepository;
import com.ggsoft.poc.services.dto.GroupDTO;
import com.ggsoft.poc.services.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Victor Gil on 3/26/2017.
 */
@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupMapper groupMapper;


    @Transactional(readOnly = true)
    public List<GroupDTO> getGroupsContainingUser(String userId) {
        return userRepository.findById(userId)
                .map(User::getMemberships)
                .map(groups -> groups.stream().map(groupMapper::toGroupDto).collect(Collectors.toList()))
                .orElse(Collections.emptyList());


    }

    public Optional<GroupDTO> getGroup(String id) {
        return groupRepository.findById(id).map(groupMapper::toGroupDto);
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll()
                .stream()
                .map(groupMapper::toGroupDto)
                .collect(Collectors.toList());
    }

    public GroupDTO createGroup(GroupDTO dto) {
        dto.setId(UUID.randomUUID().toString());
        Group group = groupMapper.fromGroupDto(dto);
        return groupMapper.toGroupDto(groupRepository.save(group));
    }

    @Transactional
    public GroupDTO addGroupMember(String userId, String groupId) {
        Optional<User> user = userRepository.findById(userId);
//        user.
        if (user.isPresent()) {
            return groupRepository.findById(groupId)
                    .map(group -> {
                        group.getMembers().add(user.get());
                        return groupRepository.save(group);
                    })
                    .map(groupMapper::toGroupDto).get();

        } else {
            throw new UserDoesNotExist();
        }
    }
}
