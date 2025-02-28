package ch.jubnl.vsecureflow.backend.mapper;

import ch.jubnl.vsecureflow.backend.dto.SecurityUserDto;
import ch.jubnl.vsecureflow.backend.entity.SecurityUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SecurityUserMapper extends BaseMapper<SecurityUser, SecurityUserDto> {
}
