package ch.jubnl.vsecureflow.backend.service;

import ch.jubnl.vsecureflow.backend.dto.SecurityUserDto;
import ch.jubnl.vsecureflow.backend.entity.SecurityUser;
import ch.jubnl.vsecureflow.backend.mapper.SecurityUserMapper;
import ch.jubnl.vsecureflow.backend.repository.SecurityUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityUserService extends BaseService<SecurityUser, SecurityUserDto, SecurityUserRepository, SecurityUserMapper> {
    public SecurityUserService(SecurityUserRepository repository, SecurityUserMapper mapper) {
        super(repository, mapper);
    }

    public Optional<SecurityUserDto> findByEmail(String email) {
        return this.repository.findByUsername(email).map(mapper::toDto);
    }
}
