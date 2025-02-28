package ch.jubnl.vsecureflow.backend.repository;

import ch.jubnl.vsecureflow.backend.entity.SecurityRight;

public interface SecurityRightRepository extends BaseRepository<SecurityRight, Long> {
    boolean existsByName(String name);
}