package com.homework.eco.restDocs.global.helper;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public interface DepartmentControllerTestHelper extends ControllerTestHelper{

    String DEPARTMENT_URL = "/department";
    String RESOURCE_URI = "/{departmentId}";

    String DEPARTMENT_LOCATION_URL = "/location/{departmentId}";

    default String getUrl() {
        return DEPARTMENT_URL;
    }

    default String getURI() {
        return DEPARTMENT_URL + RESOURCE_URI;
    }

    default String getDepartmentLocationURI() {
        return DEPARTMENT_URL + DEPARTMENT_LOCATION_URL;
    }

    default List<ParameterDescriptor> getDepartmentRequestPathParameterDescriptor() {
        return Arrays.asList(
                parameterWithName("departmentId").description("Department 식별자 ID")
        );
    }

    default List<FieldDescriptor> getDepartmentResponseDescriptors(DataResponseType dataResponseType) {

        String parentPath = getDataParentPath(dataResponseType);

        return List.of(
                fieldWithPath(parentPath.concat("departmentId")).type(JsonFieldType.NUMBER).description("Department 식별자 ID"),
                fieldWithPath(parentPath.concat("departmentName")).type(JsonFieldType.STRING).description("Department 이름"),
                fieldWithPath(parentPath.concat("managerId")).type(JsonFieldType.NUMBER).description("Department Manager 의 식별자 ID"),
                fieldWithPath(parentPath.concat("locationId")).type(JsonFieldType.NUMBER).description("Department Location 의 식별자 ID")
        );

    }

    default List<FieldDescriptor> getDepartmentLocationResponseDescriptors(DataResponseType dataResponseType) {

        String parentPath = getDataParentPath(dataResponseType);

        return List.of(
                fieldWithPath(parentPath.concat("departmentName")).type(JsonFieldType.STRING).description("Department 이름"),
                fieldWithPath(parentPath.concat("locationId")).type(JsonFieldType.NUMBER).description("Department Location 의 식별자 ID"),
                fieldWithPath(parentPath.concat("streetAddress")).type(JsonFieldType.STRING).description("주소"),                fieldWithPath(parentPath.concat("postalCode")).type(JsonFieldType.STRING).description("우편번호"),
                fieldWithPath(parentPath.concat("city")).type(JsonFieldType.STRING).description("도시"),
                fieldWithPath(parentPath.concat("stateProvince")).type(JsonFieldType.STRING).description("행정구역"),
                fieldWithPath(parentPath.concat("countryId")).type(JsonFieldType.STRING).description("국가")
                );
    }
}
