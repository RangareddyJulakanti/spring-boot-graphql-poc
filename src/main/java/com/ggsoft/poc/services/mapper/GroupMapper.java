package com.ggsoft.poc.services.mapper;

import com.ggsoft.poc.domain.Group;
import com.ggsoft.poc.domain.User;
import com.ggsoft.poc.services.dto.GroupDTO;
import com.ggsoft.poc.services.dto.MembershipDTO;
import com.ggsoft.poc.services.dto.UserWithoutMembershipsDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.Set;

/**
 * Created by Axxiome on 3/26/2017.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface GroupMapper {

    MembershipDTO toMembershipDto(Group group);

    @InheritInverseConfiguration
    Group fromMembershipDto(MembershipDTO membershipDTO);


    GroupDTO toGroupDto(Group group);

    @IterableMapping(elementTargetType = UserWithoutMembershipsDTO.class)
    Set<UserWithoutMembershipsDTO> members(Set<User> users);

    @InheritInverseConfiguration(name = "toGroupDto")
    Group fromGroupDto(GroupDTO groupDTO);
}
