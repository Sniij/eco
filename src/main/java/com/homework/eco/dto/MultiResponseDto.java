package com.homework.eco.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
@Getter
public class MultiResponseDto<T> {
    private List<T> data;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
    public MultiResponseDto(){
        this.data = (List<T>) List.of("해당하는 데이터가 존재하지 않습니다.");
        this.pageInfo = new PageInfo(1,
                0, 0, 1);
    }
    public MultiResponseDto(List<T> data, PageInfo pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
    }
}