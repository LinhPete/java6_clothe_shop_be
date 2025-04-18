package com.linhpete.java6.presentation.request_handler.mapper;

import com.linhpete.java6.presentation.request_handler.dto.request.OrderRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.OrderResponse;
import com.linhpete.java6.persistence.entity.Order;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface OrderMapper extends AbstractMapper<Order, OrderRequest, OrderResponse> {
}
