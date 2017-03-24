package com.ggsoft.poc.repos;

import com.ggsoft.poc.domain.Group;
import com.ggsoft.poc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Victor Gil on 3/23/2017.
 */
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByMembershipsContaining(Group group);


    Optional<User> findById(String s);
}
