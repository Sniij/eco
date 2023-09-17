package com.homework.eco.restDocs.global.helper;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public interface JobControllerTestHelper extends ControllerTestHelper{
    String EMPLOYEE_HISTORY_URL = "/employee/history";
    String RESOURCE_URI = "/{employeeId}";

    default String getEmployeeHistoryUrl() {
        return EMPLOYEE_HISTORY_URL;
    }

    default String getEmployeeHistoryURI() {
        return EMPLOYEE_HISTORY_URL + RESOURCE_URI;
    }


    default List<ParameterDescriptor> getEmployeeRequestPathParameterDescriptor() {
        return Arrays.asList(
                parameterWithName("employeeId").description("EmployeeId 식별자 ID")
        );
    }

    default List<FieldDescriptor> getEmployeeJobHistoryResponseDescriptors(DataResponseType dataResponseType) {

        String parentPath = getDataParentPath(dataResponseType);

        return List.of(
                fieldWithPath(parentPath.concat("employeeId")).type(JsonFieldType.NUMBER).description("Employee 식별자 ID"),
                fieldWithPath(parentPath.concat("startDate")).type(JsonFieldType.STRING).description("계약기간 시작"),
                fieldWithPath(parentPath.concat("endDate")).type(JsonFieldType.STRING).description("계약기간 끝"),
                fieldWithPath(parentPath.concat("jobId")).type(JsonFieldType.STRING).description("Job 식별자 ID"),
                fieldWithPath(parentPath.concat("departmentId")).type(JsonFieldType.NUMBER).description("Department 의 식별자 ID")
        );

    }
}
