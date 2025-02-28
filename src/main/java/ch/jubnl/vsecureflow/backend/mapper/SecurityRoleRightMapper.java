package ch.jubnl.vsecureflow.backend.mapper;

import ch.jubnl.vsecureflow.backend.dto.SecurityRoleRightDto;
import ch.jubnl.vsecureflow.backend.entity.SecurityRoleRight;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SecurityRoleRightMapper extends BaseMapper<SecurityRoleRight, SecurityRoleRightDto> {
}