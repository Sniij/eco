package com.homework.eco.restDocs.v1.employee;


import com.google.gson.Gson;
import com.homework.eco.employee.controller.EmployeeController;
import com.homework.eco.employee.dto.EmployeeDto;
import com.homework.eco.employee.entity.Employee;
import com.homework.eco.employee.mapper.EmployeeMapper;
import com.homework.eco.employee.service.EmployeeService;
import com.homework.eco.restDocs.global.helper.EmployeeControllerTestHelper;
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
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.List;

import static com.homework.eco.restDocs.global.utils.ApiDocumentUtils.getRequestPreProcessor;
import static com.homework.eco.restDocs.global.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class EmployeeControllerTest implements EmployeeControllerTestHelper {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private EmployeeMapper employeeMapper;


    @DisplayName("Test - EmployeeController - GET")
    @Test
    public void getEmployee() throws Exception {

        // given
        Employee employee = StubData.MockEmployee.getSingleEmployee();
        EmployeeDto.Detail response = StubData.MockEmployee.getSingleEmployeeDetail();
        response.setCommissionPct(BigDecimal.valueOf(0));
        given(employeeService.findEmployee(Mockito.anyInt())).willReturn(employee);
        given(employeeMapper.entityToDetailResponse(Mockito.any(Employee.class))).willReturn(response);


        // when
        ResultActions actions = mockMvc.perform(
                getRequestBuilder(getURI(), employee.getId())
        );


        // then
        actions.andExpect(status().isOk())
                .andDo(
                        document("get-employee",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getEmployeeRequestPathParameterDescriptor()
                                ),
                                responseFields(
                                        getFullResponseDescriptors(getEmployeeResponseDescriptors(DataResponseType.SINGLE))
                                )
                        )
                );

    }

    @DisplayName("Test - EmployeeController - PATCH")
    @Test
    public void patchEmployee() throws Exception {

        // given
        EmployeeDto.Patch patch = (EmployeeDto.Patch) StubData.MockEmployee.getRequestBody(HttpMethod.PATCH);
        Employee employee = StubData.MockEmployee.getSingleEmployee();
        EmployeeDto.Detail response = StubData.MockEmployee.getSingleEmployeeDetail();
        response.setCommissionPct(BigDecimal.valueOf(0));

        given(employeeService.updateEmployee(Mockito.any(EmployeeDto.Patch.class))).willReturn(employee);
        given(employeeMapper.entityToDetailResponse(Mockito.any(Employee.class))).willReturn(response);

        String content = gson.toJson(patch);


        // when
        ResultActions actions = mockMvc.perform(
                patchRequestBuilder(getUrl(), content)
        );


        // then
        actions
                .andExpect(status().isOk())
                .andDo(document("patch-employee",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                getEmployeePatchDescriptors()
                        ),
                        responseFields(
                                getFullResponseDescriptors(
                                        getEmployeeResponseDescriptors(DataResponseType.SINGLE))
                        )
                ));

    }

    @DisplayName("Test - EmployeeController - PATCH - Salary")
    @Test
    public void patchDepartmentSalary() throws Exception {

        // given
        int departmentId = 10;
        String increment = "1.1";
        List<Employee> employeeList = StubData.MockEmployee.getMultiEmployee();
        List<EmployeeDto.Detail> responseList = StubData.MockEmployee.getMultiEmployeeDetail();


        given(employeeService.findEmployeeByDepartmentId(Mockito.anyInt())).willReturn(employeeList);
        given(employeeService.updateEmployeeList(Mockito.anyList())).willReturn(employeeList);
        given(employeeMapper.entityListToDetailList(Mockito.anyList())).willReturn(responseList);


        // when
        ResultActions actions = mockMvc.perform(
                patchRequestBuilder(getDepartmentURI(), departmentId,increment)
        );


        // then
        actions.andExpect(status().isOk())
                .andDo(
                        document("patch-employee-salary",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getDepartmentRequestPathParameterDescriptor()
                                ),
                                requestParameters(
                                        getDepartmentRequestParameterDescriptor()
                                ),
                                responseFields(
                                        getFullResponseDescriptors(getEmployeeResponseDescriptors(DataResponseType.LIST))
                                )
                        )
                );

    }
}
