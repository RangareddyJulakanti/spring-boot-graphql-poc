package com.ggsoft.poc.graphql.dataFetchers;

import com.ggsoft.poc.domain.Group;
import com.ggsoft.poc.domain.User;
import com.ggsoft.poc.domain.builders.GroupBuilder;
import com.ggsoft.poc.repos.GroupRepository;
import com.ggsoft.poc.repos.UserRepository;
import com.merapar.graphql.base.TypedValueMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Created by Victor Gil on 3/24/2017.
 */

@Component
public class GroupDataFetcher {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Group> getGroupsByFilter(TypedValueMap args) {
        String id = args.get("id");
        String userId = args.get("userId");
        if (userId != null) {
            return userRepository.findById(userId)
                    .map(user -> groupRepository
                            .findByMembersContaining(user))
                    .orElse(Collections.emptyList());

//            return groupRepository.findByMembersContaining(user);
        }
        if (id != null) {
            return Collections.singletonList(groupRepository.findOne(id));
        } else {
            return groupRepository.findAll();
        }
    }

    public List<User> getGroupMembers(Group group) {
        return userRepository.findByMembershipsContaining(group);
    }

    public Group createGroup(TypedValueMap args) {
        String id = UUID.randomUUID().toString();
        Group group = GroupBuilder.aGroup()
                .withId(id)
                .withName(args.get("name"))
                .withMembers(new HashSet<>())
                .build();
        return groupRepository.save(group);

    }

    @Transactional
    public Group addGroupMember(TypedValueMap args) {
        String groupId = args.get("groupId");
        String userId = args.get("userId");

        Group group = groupRepository.findOne(groupId);
        return userRepository.findById(userId)
                .map(user -> {
                    group.getMembers().add(user);
                    return groupRepository.save(group);
                }).orElse(group);
    }
}
