package com.homework.eco.restDocs.v1.api;

import com.google.gson.Gson;
import com.homework.eco.api.controller.APIController;
import com.homework.eco.api.dto.APIDto;
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
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.homework.eco.restDocs.global.utils.ApiDocumentUtils.getRequestPreProcessor;
import static com.homework.eco.restDocs.global.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(APIController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class APIControllerTest implements APIControllerTestHelper {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private APIServiceImpl apiService;

    @MockBean
    private RestParkService restParkService;

    @DisplayName("Test - APIController - Parking - GET")
    @Test
    public void getParkingInfo() throws Exception {

        // given
        String url = "https://example.com";
        JSONArray head = new JSONArray();
        JSONObject jsonHead = StubData.MockParking.getParkingHeaderJSONObject();
        JSONArray row = new JSONArray();
        JSONObject response = new JSONObject();

        APIDto.Parking.Header header = StubData.MockParking.getParkingHeader();
        List<APIDto.Parking.Body> body = StubData.MockParking.getParkingBody();

        given(apiService.getJsonObjectByRequestURI(Mockito.any(URL.class))).willReturn(response);
        given(apiService.getURLParkingAPI(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyString())).willReturn(url);
        given(apiService.getParkingHeadJSONArray(Mockito.any(JSONObject.class))).willReturn(head);
        given(apiService.getParkingHeadObject(Mockito.any(JSONArray.class))).willReturn(jsonHead);
        given(apiService.getParkingRowJSONArray(Mockito.any(JSONObject.class))).willReturn(row);
        given(apiService.makeParkingBodyByJSONArray(Mockito.any(JSONArray.class))).willReturn(body);

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
