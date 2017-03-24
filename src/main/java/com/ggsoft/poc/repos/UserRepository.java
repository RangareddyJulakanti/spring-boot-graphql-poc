package com.ggsoft.poc.repos;

import com.ggsoft.poc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Axxiome on 3/23/2017.
 */
public interface UserRepository extends JpaRepository<User, String> {
}
