package ch.jubnl.vsecureflow.backend.repository;

import ch.jubnl.vsecureflow.backend.entity.SecurityUser;

import java.util.Optional;

public interface SecurityUserRepository extends BaseRepository<SecurityUser, Long> {
    Optional<SecurityUser> findByUsername(String username);
}