package br.com.lowlevel.orderms.dto;

import java.util.List;

public record OrderCreatedEventDTO(
        Long codigoPedido,
        Long codigoCliente,
        List<OrderItemDTO> itens
) {
}
