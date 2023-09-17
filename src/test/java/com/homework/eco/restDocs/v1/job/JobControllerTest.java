package com.homework.eco.restDocs.v1.job;


import com.google.gson.Gson;
import com.homework.eco.job.controller.JobController;
import com.homework.eco.job.dto.JobHistoryDto;
import com.homework.eco.job.entity.JobHistory;
import com.homework.eco.job.mapper.JobHistoryMapper;
import com.homework.eco.job.mapper.JobMapper;
import com.homework.eco.job.service.JobService;
import com.homework.eco.restDocs.global.helper.JobControllerTestHelper;
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

import java.util.List;

import static com.homework.eco.restDocs.global.utils.ApiDocumentUtils.getRequestPreProcessor;
import static com.homework.eco.restDocs.global.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class JobControllerTest implements JobControllerTestHelper {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private JobService jobService;

    @MockBean
    private JobMapper jobMapper;

    @MockBean
    private JobHistoryMapper jobHistoryMapper;


    @DisplayName("Test - JobController - GET - Employee-history")
    @Test
    public void getEmployeeHistory() throws Exception {

        // given
        List<JobHistory> jobHistoryList= StubData.MockJob.getMultiJobHistory();
        List<JobHistoryDto.Response> response = StubData.MockJob.getMultiJobHistoryResponse();

        given(jobService.findHistories(Mockito.anyInt())).willReturn(jobHistoryList);
        given(jobHistoryMapper.entityListToResponseList(Mockito.anyList())).willReturn(response);


        // when
        ResultActions actions = mockMvc.perform(
                getRequestBuilder(getEmployeeHistoryURI(), 200)
        );


        // then
        actions.andExpect(status().isOk())
                .andDo(
                        document("get-job-employee-history",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getEmployeeRequestPathParameterDescriptor()
                                ),
                                responseFields(
                                        getFullResponseDescriptors(getEmployeeJobHistoryResponseDescriptors(DataResponseType.LIST))
                                )
                        )
                );

    }

}
