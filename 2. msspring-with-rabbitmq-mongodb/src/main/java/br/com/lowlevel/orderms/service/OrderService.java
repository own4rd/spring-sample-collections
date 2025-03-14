package br.com.lowlevel.orderms.service;

import br.com.lowlevel.orderms.dto.OrderCreatedEventDTO;
import br.com.lowlevel.orderms.dto.OrderResponseDTO;
import br.com.lowlevel.orderms.entity.OrderEntity;
import br.com.lowlevel.orderms.entity.OrderItem;
import br.com.lowlevel.orderms.repository.OrderRepository;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Page<OrderResponseDTO> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);
        return orders.map(OrderResponseDTO::fromEntity);
    }

    public BigDecimal getTotalByCustomerId(Long customerId) {
        var aggregations = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("total").as("total")
        );
        var response = mongoTemplate.aggregate(aggregations, "orders", Document.class);
        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }

    public void save(OrderCreatedEventDTO event) {
        var entity = new OrderEntity();
        entity.setOrderId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());
        entity.setItems(getOrderItems(event));
        entity.setTotal(getTotal(event));
        orderRepository.save(entity);
    }

    private BigDecimal getTotal(OrderCreatedEventDTO event) {
        return event.itens().stream()
                .map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static List<OrderItem> getOrderItems(OrderCreatedEventDTO event) {
        return event.itens().stream().map(i -> new OrderItem(
                i.produto(),
                i.quantidade(),
                i.preco()
        )).toList();
    }
}
