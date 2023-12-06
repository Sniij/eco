package com.homework.eco.restDocs.global.helper;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public interface APIControllerTestHelper extends ControllerTestHelper{
    String PARKING_URL = "/parking";
    default String getUrl() {
        return PARKING_URL;
    }

    default List<ParameterDescriptor>         getParkingRequestParameterDescriptor() {
        return Arrays.asList(
                parameterWithName("page").description("페이지 번호").optional(),
                parameterWithName("size").description("페이지 속 원소 수").optional(),
                parameterWithName("address").description("검색할 주소지(지번 주소 기준)")
        );
    }

    default List<FieldDescriptor> getParkingResponseDescriptors() {

        String header = "pageInfo.";
        String body = "data[].";

        return List.of(
                fieldWithPath(header.concat("totalElements")).type(JsonFieldType.NUMBER).description("검색한 데이터의 총갯수"),
                fieldWithPath(header.concat("totalPages")).type(JsonFieldType.NUMBER).description("검색한 데이터의 총 페이지수"),
                fieldWithPath(header.concat("page")).type(JsonFieldType.NUMBER).description("요청한 페이지"),
                fieldWithPath(header.concat("size")).type(JsonFieldType.NUMBER).description("요청한 페이지 크기"),

                fieldWithPath(body.concat("parkingName")).type(JsonFieldType.STRING).description("주차장명"),
                fieldWithPath(body.concat("parkingDiv")).type(JsonFieldType.STRING).description("주차장 구분"),
                fieldWithPath(body.concat("parkingType")).type(JsonFieldType.STRING).description("주차장 유형"),
                fieldWithPath(body.concat("addressRoad")).type(JsonFieldType.STRING).description("도로명주소"),
                fieldWithPath(body.concat("addressLot")).type(JsonFieldType.STRING).description("지번주소"),
                fieldWithPath(body.concat("availability")).type(JsonFieldType.NUMBER).description("주차구획수"),
                fieldWithPath(body.concat("charge")).type(JsonFieldType.STRING).description("요금정보"),
                fieldWithPath(body.concat("fir_THURSDAY")).type(JsonFieldType.STRING).description("이번 추석 명절 개방여부(2023-09-28)"),
                fieldWithPath(body.concat("sec_FRIDAY")).type(JsonFieldType.STRING).description("이번 추석 명절 개방여부(2023-09-29)"),
                fieldWithPath(body.concat("trd_SATURDAY")).type(JsonFieldType.STRING).description("이번 추석 명절 개방여부(2023-09-30)"),
                fieldWithPath(body.concat("four_SUNDAY")).type(JsonFieldType.STRING).description("이번 추석 명절 개방여부(2023-10-01)"),
                fieldWithPath(body.concat("fif_MONDAY")).type(JsonFieldType.STRING).description("이번 추석 명절 개방여부(2023-10-02)"),
                fieldWithPath(body.concat("six_TUESDAY")).type(JsonFieldType.STRING).description("이번 추석 명절 개방여부(2023-10-03)")
        );

    }

}
