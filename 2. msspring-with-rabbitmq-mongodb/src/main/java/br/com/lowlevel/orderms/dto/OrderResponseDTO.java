package br.com.lowlevel.orderms.dto;

import br.com.lowlevel.orderms.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponseDTO(
        Long orderId,
        Long customerId,
        BigDecimal total
) {
    public static OrderResponseDTO fromEntity(OrderEntity entity) {
        return new OrderResponseDTO(
                entity.getOrderId(),
                entity.getCustomerId(),
                entity.getTotal()
        );
    }
}
