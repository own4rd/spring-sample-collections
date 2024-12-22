package br.com.lowlevel.orderms.dto;

import java.math.BigDecimal;

public record OrderItemDTO(
        String produto,
        Integer quantidade,
        BigDecimal preco
) {
}
