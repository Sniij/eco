package com.homework.eco.job.mapper;

import com.homework.eco.job.dto.JobHistoryDto;
import com.homework.eco.job.entity.JobHistory;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface JobHistoryMapper {


    default List<JobHistoryDto.Response> entityListToResponseList(List<JobHistory> jobHistory) {
        if ( jobHistory == null ) {
            return null;
        }

        List<JobHistoryDto.Response> list = new ArrayList<JobHistoryDto.Response>( jobHistory.size() );
        for ( JobHistory jobHistory1 : jobHistory ) {
            list.add( jobHistoryToResponse( jobHistory1 ) );
        }

        return list;
    }

    private JobHistoryDto.Response jobHistoryToResponse(JobHistory jobHistory) {
        if ( jobHistory == null ) {
            return null;
        }

        JobHistoryDto.Response response = new JobHistoryDto.Response();

        response.setEmployeeId( jobHistory.getJobHistoryId().getEmployee().getId() );
        response.setStartDate( jobHistory.getJobHistoryId().getStartDate() );
        response.setEndDate( jobHistory.getEndDate() );
        response.setJobId( jobHistory.getJobId().getId() );
        response.setDepartmentId( jobHistory.getDepartmentId().getId() );

        return response;
    }

}
