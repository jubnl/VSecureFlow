package ch.jubnl.vsecureflow.backend.service;

import ch.jubnl.vsecureflow.backend.dto.SecurityRightDto;
import ch.jubnl.vsecureflow.backend.entity.SecurityRight;
import ch.jubnl.vsecureflow.backend.mapper.SecurityRightMapper;
import ch.jubnl.vsecureflow.backend.repository.SecurityRightRepository;
import org.springframework.stereotype.Service;

@Service
public class SecurityRightService extends BaseService<SecurityRight, SecurityRightDto, SecurityRightRepository, SecurityRightMapper> {
    public SecurityRightService(SecurityRightRepository repository, SecurityRightMapper mapper) {
        super(repository, mapper);
    }

    public boolean existsByName(String name) {
        return this.repository.existsByName(name);
    }
}
