package ch.jubnl.vsecureflow.backend.mapper;

import ch.jubnl.vsecureflow.backend.dto.AuditDto;
import ch.jubnl.vsecureflow.backend.entity.Audit;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuditMapper extends BaseMapper<Audit, AuditDto> {
}