package com.ggsoft.poc.services.mapper;

import com.ggsoft.poc.domain.User;
import com.ggsoft.poc.services.dto.UserDTO;
import com.ggsoft.poc.services.dto.UserWithoutMembershipsDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Created by Victor Gil on 3/26/2017.
 */
@Mapper(componentModel = "spring", uses = {GroupMapper.class})
public interface UserMapper {


    User fromUserDTO(UserDTO dto);

    @InheritInverseConfiguration
    UserDTO toUserDTO(User user);

    User fromUserDtoWithoutMemberships(UserWithoutMembershipsDTO userWithoutMembershipsDTO);

    @InheritInverseConfiguration(name = "fromUserDtoWithoutMemberships")
    UserWithoutMembershipsDTO toUserWithoutMembershipsDto(User user);

    void updateUserInformation(UserWithoutMembershipsDTO dto, @MappingTarget User user);
}
