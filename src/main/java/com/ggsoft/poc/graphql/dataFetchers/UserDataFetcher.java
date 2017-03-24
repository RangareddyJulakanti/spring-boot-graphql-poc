package com.ggsoft.poc.graphql.dataFetchers;

import com.ggsoft.poc.domain.Group;
import com.ggsoft.poc.domain.User;
import com.ggsoft.poc.domain.builders.UserBuilder;
import com.ggsoft.poc.repos.GroupRepository;
import com.ggsoft.poc.repos.UserRepository;
import com.merapar.graphql.base.TypedValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Victor Gil on 3/23/2017.
 */

@Component
public class UserDataFetcher {

    private static final Logger logger = LoggerFactory.getLogger(UserDataFetcher.class);

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private GroupRepository groupRepository;

    public List<User> getUsersByFilter(TypedValueMap arguments) {
        String id = arguments.get("id");
        logger.info("Getting users");
        if (id != null) {
            logger.info("ID is {}", id);
            return Collections.singletonList(userRepository.findOne(id));
        } else {
            return userRepository.findAll();
        }
    }

    public List<Group> getMemberships(User user) {
        return groupRepository.findByMembersContaining(user);
    }

    public User addUser(TypedValueMap arguments) {
        String generatedId = UUID.randomUUID().toString();
        logger.info("Creating new user with ID {}", generatedId);
        User user = UserBuilder.anUser()
                .withId(generatedId)
                .withEmail(arguments.get("email"))
                .withFullName(arguments.get("fullName"))
                .withPhoneNumber(arguments.get("phoneNumber"))
                .withStreetAddress(arguments.get("streetAddress"))
                .build();
        return userRepository.save(user);
    }

    public User updateUser(TypedValueMap arguments) {
        String id = arguments.get("id");
        logger.info("Updating user {}", id);
        User user = userRepository.findOne(id);
        if (arguments.containsKey("email"))
            user.setEmail(arguments.get("email"));
        if (arguments.containsKey("fullName"))
            user.setFullName(arguments.get("fullName"));
        if (arguments.containsKey("phoneNumber"))
            user.setPhoneNumber(arguments.get("phoneNumber"));
        if (arguments.containsKey("streetAddress"))
            user.setStreetAddress(arguments.get("streetAddress"));

        return userRepository.save(user);
    }

    public String removeUser(TypedValueMap args) {
        String id = args.get("id");
        logger.info("Removing user {}", id);
        userRepository.delete(id);
        return id;
    }


    @PostConstruct
    public void init() {
        logger.info("Initializing UserDataFetcher");
    }
}
