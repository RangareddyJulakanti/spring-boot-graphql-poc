package com.ggsoft.poc.graphql.dataFetchers;

import com.ggsoft.poc.domain.User;
import com.ggsoft.poc.domain.builders.UserBuilder;
import com.ggsoft.poc.repos.UserRepository;
import com.merapar.graphql.base.TypedValueMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Axxiome on 3/23/2017.
 */

@Component
public class UserDataFetcher {

    @Autowired
    private UserRepository userRepository;


    public List<User> getUsersByFilter(TypedValueMap arguments) {
        String id = arguments.get("id");

        if (id != null) {
            return Collections.singletonList(userRepository.findOne(id));
        } else {
            return userRepository.findAll();
        }
    }

    public User addUser(TypedValueMap arguments) {
        String generatedId = UUID.randomUUID().toString();
        User user = UserBuilder.anUser()
                .withId(generatedId)
                .withEmail(arguments.get("email"))
                .withFullName(arguments.get("fullName"))
                .withPhoneNumber(arguments.get("phoneNumber"))
                .withStreetAddress(arguments.get("streetAddress"))
                .build();
        return userRepository.save(user);
    }
}
