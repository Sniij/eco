package com.homework.eco.employee.mapper;

import com.homework.eco.department.entity.Department;
import com.homework.eco.employee.dto.EmployeeDto;
import com.homework.eco.employee.entity.Employee;
import com.homework.eco.location.entity.Country;
import com.homework.eco.location.entity.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


    default EmployeeDto.Detail entityToDetailResponse(Employee employee) {

        if ( employee == null ) {
            return null;
        }

        Department department = employee.getDepartmentId();
        Location location = department.getLocationId();
        Country country = location.getCountryId();

        EmployeeDto.Detail detail = new EmployeeDto.Detail();

        detail.setEmployeeId( employee.getId() );
        detail.setJobId( employee.getJobId().getId() );
        detail.setFirstName( employee.getFirstName() );
        detail.setLastName( employee.getLastName() );
        detail.setSalary( employee.getSalary() );
        detail.setCommissionPct( employee.getCommissionPct() );
        detail.setJobTitle( employee.getJobId().getJobTitle() );
        detail.setDepartmentName( department.getDepartmentName() );
        detail.setLocationId( location.getId() );
        detail.setCity( location.getCity() );
        detail.setStateProvince( location.getStateProvince() );
        detail.setCountryId( country.getId() );
        detail.setCountryName(country.getCountryName() );
        detail.setRegionName( country.getRegionId().getRegionName() );
        detail.setManagerId( employee.getManagerId().getId() );
        detail.setDepartmentId( department.getId() );

        return detail;
    }
}
