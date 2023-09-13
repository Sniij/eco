package com.homework.eco.job.mapper;

import com.homework.eco.job.dto.JobHistoryDto;
import com.homework.eco.job.entity.JobHistory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobHistoryMapper {

    List<JobHistoryDto.Response> entityListToResponseList(List<JobHistory> jobHistory);
}
