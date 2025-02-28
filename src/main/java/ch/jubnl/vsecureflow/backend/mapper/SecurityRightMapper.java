package ch.jubnl.vsecureflow.backend.mapper;

import ch.jubnl.vsecureflow.backend.dto.SecurityRightDto;
import ch.jubnl.vsecureflow.backend.entity.SecurityRight;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SecurityRightMapper extends BaseMapper<SecurityRight, SecurityRightDto> {
}