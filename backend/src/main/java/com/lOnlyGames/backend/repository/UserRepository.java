package com.lOnlyGames.backend.repository;

import com.lOnlyGames.backend.model.Availability;
import com.lOnlyGames.backend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
