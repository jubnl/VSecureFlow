package ch.jubnl.vsecureflow.backend.service;

import ch.jubnl.vsecureflow.backend.dto.SecurityGroupDto;
import ch.jubnl.vsecureflow.backend.entity.SecurityGroup;
import ch.jubnl.vsecureflow.backend.mapper.SecurityGroupMapper;
import ch.jubnl.vsecureflow.backend.repository.SecurityGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class SecurityGroupService extends BaseService<SecurityGroup, SecurityGroupDto, SecurityGroupRepository, SecurityGroupMapper> {
    public SecurityGroupService(SecurityGroupRepository repository, SecurityGroupMapper mapper) {
        super(repository, mapper);
    }
}
