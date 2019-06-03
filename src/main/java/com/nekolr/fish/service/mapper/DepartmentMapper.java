package com.nekolr.fish.service.mapper;

import com.nekolr.fish.entity.Department;
import com.nekolr.fish.service.dto.DepartmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper extends EntityMapper<Department, DepartmentDTO> {

}
