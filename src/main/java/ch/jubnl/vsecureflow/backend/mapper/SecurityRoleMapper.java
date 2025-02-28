package ch.jubnl.vsecureflow.backend.mapper;

import ch.jubnl.vsecureflow.backend.dto.SecurityRoleDto;
import ch.jubnl.vsecureflow.backend.entity.SecurityRole;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SecurityRoleMapper extends BaseMapper<SecurityRole, SecurityRoleDto> {
}