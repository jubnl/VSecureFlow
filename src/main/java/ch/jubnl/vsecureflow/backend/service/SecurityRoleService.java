package ch.jubnl.vsecureflow.backend.service;

import ch.jubnl.vsecureflow.backend.dto.SecurityRoleDto;
import ch.jubnl.vsecureflow.backend.entity.SecurityRole;
import ch.jubnl.vsecureflow.backend.mapper.SecurityRoleMapper;
import ch.jubnl.vsecureflow.backend.repository.SecurityRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class SecurityRoleService extends BaseService<SecurityRole, SecurityRoleDto, SecurityRoleRepository, SecurityRoleMapper> {
    public SecurityRoleService(SecurityRoleRepository repository, SecurityRoleMapper mapper) {
        super(repository, mapper);
    }
}
