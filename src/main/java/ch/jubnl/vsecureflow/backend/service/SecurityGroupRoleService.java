package ch.jubnl.vsecureflow.backend.service;

import ch.jubnl.vsecureflow.backend.dto.SecurityGroupRoleDto;
import ch.jubnl.vsecureflow.backend.entity.SecurityGroupRole;
import ch.jubnl.vsecureflow.backend.mapper.SecurityGroupRoleMapper;
import ch.jubnl.vsecureflow.backend.repository.SecurityGroupRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class SecurityGroupRoleService extends BaseService<SecurityGroupRole, SecurityGroupRoleDto, SecurityGroupRoleRepository, SecurityGroupRoleMapper> {
    public SecurityGroupRoleService(SecurityGroupRoleRepository repository, SecurityGroupRoleMapper mapper) {
        super(repository, mapper);
    }
}
