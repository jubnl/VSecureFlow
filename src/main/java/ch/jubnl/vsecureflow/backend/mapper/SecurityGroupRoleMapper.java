package ch.jubnl.vsecureflow.backend.mapper;

import ch.jubnl.vsecureflow.backend.dto.SecurityGroupRoleDto;
import ch.jubnl.vsecureflow.backend.entity.SecurityGroupRole;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SecurityGroupRoleMapper extends BaseMapper<SecurityGroupRole, SecurityGroupRoleDto> {
}