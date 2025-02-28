package ch.jubnl.vsecureflow.backend.service;

import ch.jubnl.vsecureflow.backend.dto.SecurityUserGroupDto;
import ch.jubnl.vsecureflow.backend.entity.SecurityUserGroup;
import ch.jubnl.vsecureflow.backend.mapper.SecurityUserGroupMapper;
import ch.jubnl.vsecureflow.backend.repository.SecurityUserGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserGroupService extends BaseService<SecurityUserGroup, SecurityUserGroupDto, SecurityUserGroupRepository, SecurityUserGroupMapper> {
    public SecurityUserGroupService(SecurityUserGroupRepository repository, SecurityUserGroupMapper mapper) {
        super(repository, mapper);
    }
}
