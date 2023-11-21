package com.sdk.itjobs.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResponse<T> {
    private List<T> content;
    private Long page;
    private Long size;
    private Long totalPages;

    public PageResponse(List<T> content, Long pageIndex, Long size, Long totalPages) {
        this.content = content;
        this.page = pageIndex + 1;
        this.size = size;
        this.totalPages = totalPages;
    }
}
