package com.ggsoft.poc.graphql.dataFetchers;

import com.ggsoft.poc.services.UserService;
import com.ggsoft.poc.services.dto.UserDTO;
import com.ggsoft.poc.services.dto.UserDTOBuilder;
import com.ggsoft.poc.services.dto.UserWithoutMembershipsDTO;
import com.merapar.graphql.base.TypedValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

/**
 * Created by Victor Gil on 3/23/2017.
 */

@Component
public class UserDataFetcher {

    private static final Logger logger = LoggerFactory.getLogger(UserDataFetcher.class);

    @Autowired
    private UserService userService;


    public List<UserDTO> getUsersByFilter(TypedValueMap arguments) {
        String id = arguments.get("id");
        logger.info("Getting users");
        if (id != null) {
            logger.info("ID is {}", id);
            return Collections.singletonList(userService.getUser(id).get());
        } else {
            return userService.getAllUsers();
        }
    }

//    public List<GroupDTO> getMemberships(UserDTO user) {
//        return userService.getMemberships(user);//groupRepository.findByMembersContaining(user);
//    }

    public UserDTO addUser(TypedValueMap arguments) {
        logger.info("Creating new user with ID ");
        UserDTO userDTO = UserDTOBuilder.anUserDTO()
                .withEmail(arguments.get("email"))
                .withFullName(arguments.get("fullName"))
                .withPhoneNumber(arguments.get("phoneNumber"))
                .withStreetAddress(arguments.get("streetAddress"))
                .build();
        return userService.createUser(userDTO);
    }

    public UserWithoutMembershipsDTO updateUser(TypedValueMap arguments) {
        String id = arguments.get("id");
        logger.info("Updating user {}", id);
//        User user = userRepository.findOne(id);
        UserWithoutMembershipsDTO dto = new UserWithoutMembershipsDTO();
        dto.setId(id);
        if (arguments.containsKey("email"))
            dto.setEmail(arguments.get("email"));
        if (arguments.containsKey("fullName"))
            dto.setFullName(arguments.get("fullName"));
        if (arguments.containsKey("phoneNumber"))
            dto.setPhoneNumber(arguments.get("phoneNumber"));
        if (arguments.containsKey("streetAddress"))
            dto.setStreetAddress(arguments.get("streetAddress"));

        return userService.updateUser(dto);

    }

    public String removeUser(TypedValueMap args) {
        String id = args.get("id");
        logger.info("Removing user {}", id);
//        userRepository.delete(id);
        userService.removeUser(id);
        return id;
    }


    @PostConstruct
    public void init() {
        logger.info("Initializing UserDataFetcher");
    }
}
