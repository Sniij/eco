package com.homework.eco.restDocs.v1.api;

import com.google.gson.Gson;
import com.homework.eco.api.restpark.controller.RestParkController;
import com.homework.eco.api.restpark.dto.RestPartDto;
import com.homework.eco.api.restpark.service.RestParkService;
import com.homework.eco.api.service.APIServiceImpl;
import com.homework.eco.restDocs.global.helper.APIControllerTestHelper;
import com.homework.eco.restDocs.global.mock.StubData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URL;
import java.util.List;

import static com.homework.eco.restDocs.global.utils.ApiDocumentUtils.getRequestPreProcessor;
import static com.homework.eco.restDocs.global.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestParkController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class RestParkControllerTest implements APIControllerTestHelper {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private APIServiceImpl apiService;

    @MockBean
    private RestParkService restParkService;

    @DisplayName("Test - RestParkController - Parking - GET")
    @Test
    public void getParkingInfo() throws Exception {

        // given
        Page<RestPartDto.Parking.Body> body = StubData.MockParking.getParkingBodyPage();

        given(restParkService.getParkingInfo(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString())).willReturn(body);

        String page = "1";
        String size = "2";
        String address = "군포시";
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);
        queryParams.add("address", address);


        // when
        ResultActions actions = mockMvc.perform(
                getRequestBuilder(getUrl(), queryParams)
        );


        // then
        actions.andExpect(status().isOk())
                .andDo(
                        document("get-parking",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                requestParameters(
                                        getParkingRequestParameterDescriptor()
                                ),
                                responseFields(
                                        getFullResponseDescriptors(getParkingResponseDescriptors())
                                )
                        )
                );

    }
}
