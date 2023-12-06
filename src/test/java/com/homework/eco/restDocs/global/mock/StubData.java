package com.homework.eco.restDocs.global.mock;

import com.homework.eco.api.restpark.dto.RestPartDto;
import com.homework.eco.department.dto.DepartmentDto;
import com.homework.eco.department.entity.Department;
import com.homework.eco.employee.dto.EmployeeDto;
import com.homework.eco.employee.entity.Employee;
import com.homework.eco.job.dto.JobHistoryDto;
import com.homework.eco.job.entity.JobHistory;
import com.homework.eco.location.dto.LocationDto;
import com.homework.eco.location.entity.Location;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;

import java.math.BigDecimal;
import java.text.ParseException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StubData {


    public static class MockDepartment{


        public static DepartmentDto.Detail getSingleDetail(){
            return DepartmentDto.Detail.builder()
                    .departmentId(10)
                    .departmentName("Administration")
                    .managerId(200)
                    .locationId(1700)
                    .build();
        }



        public static Department getSingleDepartment(){
            return Department.builder()
                    .id(10)
                    .departmentName("Administration")
                    .build();
        }
        public static Department getSingleDepartment(int departmentId){
            return Department.builder()
                    .id(departmentId)
                    .departmentName("Administration")
                    .build();
        }
    }

    public static class MockLocation{

        public static LocationDto.Detail getSingleDepartmentLocationDetail(){
            return LocationDto.Detail.builder()
                    .departmentName("Administration")
                    .locationId(1700)
                    .streetAddress("2004 Charade Rd")
                    .postalCode("98199")
                    .city("Seattle")
                    .stateProvince("Washington")
                    .countryId("US")
                    .build();
        }

        public static Location getSingleLocation(){
            return Location.builder()
                    .id(1700)
                    .streetAddress("2004 Charade Rd")
                    .postalCode("98199")
                    .city("Seattle")
                    .stateProvince("Washington")
                    .build();
        }
        public static Location getSingleLocation(int locationId){
            return Location.builder()
                    .id(locationId)
                    .streetAddress("2004 Charade Rd")
                    .postalCode("98199")
                    .city("Seattle")
                    .stateProvince("Washington")
                    .build();
        }
    }


    public static class MockEmployee{

        private static Map<HttpMethod, Object> stubRequestBody;
        static {

            EmployeeDto.Patch patchDto = EmployeeDto.Patch.builder()
                    .employeeId(200)
                    .firstName("Jennifer")
                    .lastName("Whalen")
                    .email("JWHALEN")
                    .phoneNumber("515.123.4444")
                    .jobId("AD_ASST")
                    .salary(BigDecimal.valueOf(4400.00))
                    .commissionPct(BigDecimal.valueOf(0))
                    .managerId(101)
                    .departmentId(10)
                    .build();

            stubRequestBody = new HashMap<>();
            stubRequestBody.put(HttpMethod.PATCH, patchDto);
        }
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static Employee getSingleEmployee(){
            return Employee.builder()
                    .id(200)
                    .firstName("Jennifer")
                    .lastName("Whalen")
                    .email("JWHALEN")
                    .phoneNumber("515.123.4444")
                    .hireDate(Date.valueOf("1987-09-17"))
                    //.jobId()
                    .salary(BigDecimal.valueOf(4400.00))
                    //.commissionPct("null")
                    //.managerId()
                    //.departmentId()
                    .build();
        }

        public static Employee getSingleEmployee(int employeeId){
            return Employee.builder()
                    .id(employeeId)
                    .firstName("Jennifer")
                    .lastName("Whalen")
                    .email("JWHALEN")
                    .phoneNumber("515.123.4444")
                    .hireDate(Date.valueOf("1987-09-17"))
                    //.jobId()
                    .salary(BigDecimal.valueOf(4400.00))
                    //.commissionPct("null")
                    //.managerId()
                    //.departmentId()
                    .build();
        }

        public static EmployeeDto.Detail getSingleEmployeeDetail(){
            return EmployeeDto.Detail.builder()
                    .employeeId(200)
                    .firstName("Jennifer")
                    .lastName("Whalen")
                    .jobId("AD_ASST")
                    .salary(BigDecimal.valueOf(4400.00))
                    //.commissionPct("null")
                    .managerId(101)
                    .departmentId(10)
                    .jobTitle("Administration Assistant")
                    .departmentName("Administration")
                    .locationId(1700)
                    .city("Seattle")
                    .stateProvince("Washington")
                    .countryId("US")
                    .countryName("United States of America")
                    .regionName("Americas")
                    .build();
        }

        public static EmployeeDto.Detail getSingleEmployeeDetail(int employeeId){
            return EmployeeDto.Detail.builder()
                    .employeeId(employeeId)
                    .firstName("Jennifer")
                    .lastName("Whalen")
                    .jobId("AD_ASST")
                    .salary(BigDecimal.valueOf(4400.00))
                    //.commissionPct("null")
                    .managerId(101)
                    .departmentId(10)
                    .jobTitle("Administration Assistant")
                    .departmentName("Administration")
                    .locationId(1700)
                    .city("Seattle")
                    .stateProvince("Washington")
                    .countryId("US")
                    .countryName("United States of America")
                    .regionName("Americas")
                    .build();
        }


        public static List<Employee> getMultiEmployee(){
            return List.of(Employee.builder()
                    .id(200)
                    .firstName("Jennifer")
                    .lastName("Whalen")
                    .email("JWHALEN")
                    .phoneNumber("515.123.4444")
                    .hireDate(Date.valueOf("1987-09-17"))
                    //.jobId()
                    .salary(BigDecimal.valueOf(4400.00))
                    //.commissionPct("null")
                    //.managerId()
                    //.departmentId()
                    .build());
        }

        public static List<EmployeeDto.Detail> getMultiEmployeeDetail(){
            return List.of(
                    EmployeeDto.Detail.builder()
                            .employeeId(200)
                            .firstName("Jennifer")
                            .lastName("Whalen")
                            .jobId("AD_ASST")
                            .salary(BigDecimal.valueOf(4400.00))
                            .commissionPct(BigDecimal.valueOf(0))
                            .managerId(101)
                            .departmentId(10)
                            .jobTitle("Administration Assistant")
                            .departmentName("Administration")
                            .locationId(1700)
                            .city("Seattle")
                            .stateProvince("Washington")
                            .countryId("US")
                            .countryName("United States of America")
                            .regionName("Americas")
                            .build()
            );
        }

    }

    public static class MockJob{

        public static List<JobHistory> getMultiJobHistory(){
            return List.of(
            );
        }

        public static List<JobHistoryDto.Response> getMultiJobHistoryResponse() throws ParseException {

            Date startDate1 =  Date.valueOf("1987-09-17");
            Date endDate1 =  Date.valueOf("1993-06-17");
            Date startDate2 =  Date.valueOf("1994-07-01");
            Date endDate2 =  Date.valueOf("1998-12-31");

            return List.of(
                    JobHistoryDto.Response.builder()
                            .employeeId(200)
                            .startDate(startDate1)
                            .endDate(endDate1)
                            .jobId("AD_ASST")
                            .departmentId(90)
                            .build(),
                    JobHistoryDto.Response.builder()
                            .employeeId(200)
                            .startDate(startDate2)
                            .endDate(endDate2)
                            .jobId("AC_ACCOUNT")
                            .departmentId(90)
                            .build()
            );
        }
    }


    public static class MockParking{

        public static JSONObject getParkingHeaderJSONObject(){
            JSONObject result = new JSONObject();

            JSONObject header = new JSONObject();
            header.put("totalCount", 66L);
            header.put("code", "INFO_000");
            header.put("massage", "정상 처리되었습니다.");

            result.put("header", header);

            return result;
        }

        public static RestPartDto.Parking.Header getParkingHeader(){

            return new RestPartDto.Parking.Header(getParkingHeaderJSONObject());
        }

        public static List<RestPartDto.Parking.Body> getParkingBody(){
            RestPartDto.Parking.Body body1 = RestPartDto.Parking.Body.builder()
                    .parkingName("당산로 노상")
                    .parkingDiv("공영")
                    .parkingType("노상")
                    .addressRoad("null")
                    .addressLot("경기도 군포시 당동 798")
                    .availability(116L)
                    .charge("무료")
                    .FIR_THURSDAY("종일개방")
                    .SEC_FRIDAY("종일개방")
                    .TRD_SATURDAY("종일개방")
                    .FOUR_SUNDAY("종일개방")
                    .FIF_MONDAY("종일개방")
                    .SIX_TUESDAY("종일개방")
                    .build();

            RestPartDto.Parking.Body body2 = RestPartDto.Parking.Body.builder()
                    .parkingName("고랑치기공영주차장")
                    .parkingDiv("공영")
                    .parkingType("노외")
                    .addressRoad("경기도 군포시 군포첨단산업2로 13")
                    .addressLot("경기도 군포시 부곡동 1246-1번지")
                    .availability(165L)
                    .charge("혼합")
                    .FIR_THURSDAY("해당없음(데이터 없음)")
                    .SEC_FRIDAY("해당없음(데이터 없음)")
                    .TRD_SATURDAY("해당없음(데이터 없음)")
                    .FOUR_SUNDAY("해당없음(데이터 없음)")
                    .FIF_MONDAY("해당없음(데이터 없음)")
                    .SIX_TUESDAY("해당없음(데이터 없음)")
                    .build();

            List<RestPartDto.Parking.Body> body = new ArrayList();
            body.add(body1);
            body.add(body2);

            return body;
        }
        public static Page<RestPartDto.Parking.Body> getParkingBodyPage(){
            RestPartDto.Parking.Body body1 = RestPartDto.Parking.Body.builder()
                    .parkingName("당산로 노상")
                    .parkingDiv("공영")
                    .parkingType("노상")
                    .addressRoad("null")
                    .addressLot("경기도 군포시 당동 798")
                    .availability(116L)
                    .charge("무료")
                    .FIR_THURSDAY("종일개방")
                    .SEC_FRIDAY("종일개방")
                    .TRD_SATURDAY("종일개방")
                    .FOUR_SUNDAY("종일개방")
                    .FIF_MONDAY("종일개방")
                    .SIX_TUESDAY("종일개방")
                    .build();

            RestPartDto.Parking.Body body2 = RestPartDto.Parking.Body.builder()
                    .parkingName("고랑치기공영주차장")
                    .parkingDiv("공영")
                    .parkingType("노외")
                    .addressRoad("경기도 군포시 군포첨단산업2로 13")
                    .addressLot("경기도 군포시 부곡동 1246-1번지")
                    .availability(165L)
                    .charge("혼합")
                    .FIR_THURSDAY("해당없음(데이터 없음)")
                    .SEC_FRIDAY("해당없음(데이터 없음)")
                    .TRD_SATURDAY("해당없음(데이터 없음)")
                    .FOUR_SUNDAY("해당없음(데이터 없음)")
                    .FIF_MONDAY("해당없음(데이터 없음)")
                    .SIX_TUESDAY("해당없음(데이터 없음)")
                    .build();

            List<RestPartDto.Parking.Body> body = new ArrayList();
            body.add(body1);
            body.add(body2);


            return new PageImpl<>(body, PageRequest.of(1, 2), 2);
        }
    }

}
