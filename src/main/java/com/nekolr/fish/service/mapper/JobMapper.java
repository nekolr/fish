package com.nekolr.fish.service.mapper;

import com.nekolr.fish.entity.Job;
import com.nekolr.fish.service.dto.JobDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper extends EntityMapper<Job, JobDTO> {

}
