package com.homework.eco.restDocs.v1.department;


import com.google.gson.Gson;
import com.homework.eco.department.controller.DepartmentController;
import com.homework.eco.department.dto.DepartmentDto;
import com.homework.eco.department.entity.Department;
import com.homework.eco.department.mapper.DepartmentMapper;
import com.homework.eco.department.service.DepartmentService;
import com.homework.eco.location.dto.LocationDto;
import com.homework.eco.location.entity.Location;
import com.homework.eco.location.mapper.LocationMapper;
import com.homework.eco.restDocs.global.helper.DepartmentControllerTestHelper;
import com.homework.eco.restDocs.global.mock.StubData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.homework.eco.restDocs.global.utils.ApiDocumentUtils.getRequestPreProcessor;
import static com.homework.eco.restDocs.global.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DepartmentController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class DepartmentControllerTest implements DepartmentControllerTestHelper {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private DepartmentMapper departmentMapper;

    @MockBean
    private LocationMapper locationMapper;


    @DisplayName("Test - DepartmentController - GET")
    @Test
    public void getDepartment() throws Exception{

        // given
        Department department = StubData.MockDepartment.getSingleDepartment();
        DepartmentDto.Detail response = StubData.MockDepartment.getSingleDetail();

        given(departmentService.findDepartment(Mockito.anyInt())).willReturn(department);
        given(departmentMapper.entityToDetail(Mockito.any(Department.class))).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(
                getRequestBuilder(getURI(), department.getId())
        );


        // then
        actions.andExpect(status().isOk())
                .andDo(
                        document("get-department",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                  getDepartmentRequestPathParameterDescriptor()
                                ),
                                responseFields(
                                        getFullResponseDescriptors(getDepartmentResponseDescriptors(DataResponseType.SINGLE))
                                )
                        )
                );



    }


    @DisplayName("Test - DepartmentController - GET - Location")
    @Test
    public void getDepartmentLocation() throws Exception{

        // given
        Department department = StubData.MockDepartment.getSingleDepartment();
        Location location = StubData.MockLocation.getSingleLocation();
        LocationDto.Detail response = StubData.MockLocation.getSingleDepartmentLocationDetail();

        Department department1 = Department.builder()
                .id(department.getId())
                .departmentName(department.getDepartmentName())
                .locationId(location)
                .build();
        response.setDepartmentName(department1.getDepartmentName());

        given(departmentService.findDepartment(Mockito.anyInt())).willReturn(department1);
        given(locationMapper.entityToDetail(Mockito.any(Location.class))).willReturn(response);


        // when
        ResultActions actions = mockMvc.perform(
                getRequestBuilder(getDepartmentLocationURI(), department1.getId())
        );


        // then
        actions.andExpect(status().isOk())
                .andDo(
                        document("get-department-location",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getDepartmentRequestPathParameterDescriptor()
                                ),
                                responseFields(
                                        getFullResponseDescriptors(getDepartmentLocationResponseDescriptors(DataResponseType.SINGLE))
                                )
                        )
                );

    }
}
