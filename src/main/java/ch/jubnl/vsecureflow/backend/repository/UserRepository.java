package ch.jubnl.vsecureflow.backend.repository;

import ch.jubnl.vsecureflow.backend.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByEmail(String email);
}