package com.nekolr.fish.service.mapper;

import com.nekolr.fish.entity.Menu;
import com.nekolr.fish.service.EntityMapper;
import com.nekolr.fish.service.dto.MenuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends EntityMapper<Menu, MenuDTO> {

}
