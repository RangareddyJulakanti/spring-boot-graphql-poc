package com.ggsoft.poc.graphql.dataFetchers;

import com.ggsoft.poc.services.GroupService;
import com.ggsoft.poc.services.dto.GroupDTO;
import com.ggsoft.poc.services.dto.GroupDTOBuilder;
import com.ggsoft.poc.services.dto.UserWithoutMembershipsDTO;
import com.merapar.graphql.base.TypedValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Victor Gil on 3/24/2017.
 */

@Component
public class GroupDataFetcher {

    private static final Logger logger = LoggerFactory.getLogger(GroupDataFetcher.class);

    @Autowired
    private GroupService groupService;

    @Transactional(readOnly = true)
    public List<GroupDTO> getGroupsByFilter(TypedValueMap args) {
        String id = args.get("id");
        logger.info("Getting groups");
        String userId = args.get("userId");
        if (userId != null) {
            return groupService.getGroupsContainingUser(userId);
        }
        if (id != null) {
            logger.info("With id {}", id);
            return groupService.getGroup(id)
                    .map(Collections::singletonList)
                    .orElseGet(Collections::emptyList);
        } else {
            return groupService.getAllGroups();
        }
    }

    public Set<UserWithoutMembershipsDTO> getGroupMembers(GroupDTO group) {
        logger.info("Getting group members for {} - {}", group.getId(), group.getName());
        return group.getMembers();
    }

    public GroupDTO createGroup(TypedValueMap args) {
        logger.info("Creating group {}");
        GroupDTO group = GroupDTOBuilder.aGroupDTO()
                .withName(args.get("name"))
                .withMembers(new HashSet<>())
                .build();
        return groupService.createGroup(group);

    }

    @Transactional
    public GroupDTO addGroupMember(TypedValueMap args) {
        String groupId = args.get("groupId");
        String userId = args.get("userId");
        logger.info("Adding group member {} to group {}", userId, groupId);
        return groupService.addGroupMember(userId, groupId);
    }

    @PostConstruct
    public void init() {
        logger.info("Initializing GroupDataFetcher");
    }
}
