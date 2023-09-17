package com.homework.eco.restDocs.global.helper;

import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public interface EmployeeControllerTestHelper extends ControllerTestHelper{

    String EMPLOYEE_URL = "/employee";
    String RESOURCE_URI = "/{employeeId}";
    String RESOURCE_DEPARTMENT_URI = "/{departmentId}";

    default String getUrl() {
        return EMPLOYEE_URL;
    }

    default String getURI() {
        return EMPLOYEE_URL + RESOURCE_URI;
    }

    default String getDepartmentURI() {
        return EMPLOYEE_URL + RESOURCE_DEPARTMENT_URI;
    }

    default List<ParameterDescriptor> getEmployeeRequestPathParameterDescriptor() {
        return Arrays.asList(
                parameterWithName("employeeId").description("EmployeeId 식별자 ID")
        );
    }

    default List<ParameterDescriptor> getDepartmentRequestPathParameterDescriptor() {
        return Arrays.asList(
                parameterWithName("departmentId").description("Department 식별자 ID")
        );


    }

    default List<ParameterDescriptor>         getDepartmentRequestParameterDescriptor() {
        return Arrays.asList(
                parameterWithName("increment").description("임금 상승 비율")
        );
    }

    default List<FieldDescriptor> getEmployeePatchDescriptors() {


        return List.of(
                fieldWithPath("employeeId").type(JsonFieldType.NUMBER).description("Employee 식별자 ID"),
                fieldWithPath("firstName").type(JsonFieldType.STRING).description("이름").optional(),
                fieldWithPath("lastName").type(JsonFieldType.STRING).description("성").optional(),
                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일").optional(),
                fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("휴대폰 번호").optional(),
                fieldWithPath("hireDate").type(JsonFieldType.STRING).description("계약기간").optional(),
                fieldWithPath("jobId").type(JsonFieldType.STRING).description("Job 의 식별자 ID").optional(),
                fieldWithPath("salary").type(JsonFieldType.NUMBER).description("연봉").optional(),
                fieldWithPath("commissionPct").type(JsonFieldType.NUMBER).description("보너스").optional(),
                fieldWithPath("managerId").type(JsonFieldType.NUMBER).description("Manager 식별자 ID").optional(),
                fieldWithPath("departmentId").type(JsonFieldType.NUMBER).description("Department 식별자 ID").optional()
        );

    }

    default List<FieldDescriptor> getEmployeeResponseDescriptors(DataResponseType dataResponseType) {

        String parentPath = getDataParentPath(dataResponseType);

        return List.of(
                fieldWithPath(parentPath.concat("employeeId")).type(JsonFieldType.NUMBER).description("Employee 식별자 ID"),
                fieldWithPath(parentPath.concat("firstName")).type(JsonFieldType.STRING).description("이름"),
                fieldWithPath(parentPath.concat("lastName")).type(JsonFieldType.STRING).description("성"),
                fieldWithPath(parentPath.concat("jobId")).type(JsonFieldType.STRING).description("Job 의 식별자 ID"),
                fieldWithPath(parentPath.concat("salary")).type(JsonFieldType.NUMBER).description("연봉"),
                fieldWithPath(parentPath.concat("commissionPct")).type(JsonFieldType.NUMBER).description("보너스"),
                fieldWithPath(parentPath.concat("managerId")).type(JsonFieldType.NUMBER).description("Manager 식별자 ID"),
                fieldWithPath(parentPath.concat("departmentId")).type(JsonFieldType.NUMBER).description("Department 식별자 ID"),
                fieldWithPath(parentPath.concat("jobTitle")).type(JsonFieldType.STRING).description("직업명"),
                fieldWithPath(parentPath.concat("departmentName")).type(JsonFieldType.STRING).description("부서명"),
                fieldWithPath(parentPath.concat("locationId")).type(JsonFieldType.NUMBER).description("Location 식별자 ID"),
                fieldWithPath(parentPath.concat("city")).type(JsonFieldType.STRING).description("도시"),
                fieldWithPath(parentPath.concat("stateProvince")).type(JsonFieldType.STRING).description("행정구역"),
                fieldWithPath(parentPath.concat("countryId")).type(JsonFieldType.STRING).description("국가"),
                fieldWithPath(parentPath.concat("countryName")).type(JsonFieldType.STRING).description("국가명"),
                fieldWithPath(parentPath.concat("regionName")).type(JsonFieldType.STRING).description("지역명")
        );

    }


}
