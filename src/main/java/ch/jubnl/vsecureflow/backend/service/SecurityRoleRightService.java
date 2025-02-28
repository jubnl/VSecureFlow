package ch.jubnl.vsecureflow.backend.service;

import ch.jubnl.vsecureflow.backend.dto.SecurityRoleRightDto;
import ch.jubnl.vsecureflow.backend.entity.SecurityRoleRight;
import ch.jubnl.vsecureflow.backend.mapper.SecurityRoleRightMapper;
import ch.jubnl.vsecureflow.backend.repository.SecurityRoleRightRepository;
import org.springframework.stereotype.Service;

@Service
public class SecurityRoleRightService extends BaseService<SecurityRoleRight, SecurityRoleRightDto, SecurityRoleRightRepository, SecurityRoleRightMapper> {
    public SecurityRoleRightService(SecurityRoleRightRepository repository, SecurityRoleRightMapper mapper) {
        super(repository, mapper);
    }
}
