package com.homework.eco.department.mapper;

import com.homework.eco.department.dto.DepartmentDto;
import com.homework.eco.department.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    default DepartmentDto.Detail entityToDetail(Department department) {
        if ( department == null ) {
            return null;
        }
        String departmentName = null;

        DepartmentDto.Detail detail = new DepartmentDto.Detail();
        detail.setManagerId( department.getManagerId().getId() );
        detail.setLocationId( department.getLocationId().getId() );
        detail.setDepartmentId( department.getId() );
        detail.setDepartmentName( department.getDepartmentName() );

        return detail;
    }

}
