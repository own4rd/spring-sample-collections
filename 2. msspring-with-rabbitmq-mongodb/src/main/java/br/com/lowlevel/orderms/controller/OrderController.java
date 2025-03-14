package br.com.lowlevel.orderms.controller;

import br.com.lowlevel.orderms.dto.ApiResponse;
import br.com.lowlevel.orderms.dto.OrderResponseDTO;
import br.com.lowlevel.orderms.dto.PaginationResponseDTO;
import br.com.lowlevel.orderms.service.OrderService;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> listOrdersByCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        var pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, size));
        var totalOnOrders = orderService.getTotalByCustomerId(customerId);
        return ResponseEntity.ok(new ApiResponse<>(
                Map.of("totalOnOrders", totalOnOrders),
                pageResponse.getContent(),
                PaginationResponseDTO.fromPage(pageResponse)
        ));
    }

}
