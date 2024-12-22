package br.com.lowlevel.orderms.dto;

import org.springframework.data.domain.Page;

public record PaginationResponseDTO(
        Integer page,
        Integer size,
        Integer totalPages,
        Long totalElements
) {
    public static PaginationResponseDTO fromPage(Page<?> page) {
        return new PaginationResponseDTO(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }
}
