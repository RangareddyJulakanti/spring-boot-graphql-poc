package com.ggsoft.poc.repos;

import com.ggsoft.poc.domain.Group;
import com.ggsoft.poc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Victor Gil on 3/24/2017.
 */
public interface GroupRepository extends JpaRepository<Group, String> {

    List<Group> findByMembersContaining(User user);

    Optional<Group> findById(String id);
}
