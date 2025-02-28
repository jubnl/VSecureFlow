package ch.jubnl.vsecureflow.backend.mapper;

import ch.jubnl.vsecureflow.backend.dto.SecurityUserGroupDto;
import ch.jubnl.vsecureflow.backend.entity.SecurityUserGroup;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SecurityUserGroupMapper extends BaseMapper<SecurityUserGroup, SecurityUserGroupDto> {
}