package com.celalgundogdu.couriertracking.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

    private List<T> content;

    private Integer number;

    private Integer size;

    private Long totalElement;

    private Integer totalPage;

    private Boolean isFirstPage;

    private Boolean isLastPage;
}
