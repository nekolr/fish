package com.nekolr.fish.service.mapper;

import com.nekolr.fish.entity.User;
import com.nekolr.fish.service.EntityMapper;
import com.nekolr.fish.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, DepartmentMapper.class, JobMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends EntityMapper<User, UserDTO> {

}
