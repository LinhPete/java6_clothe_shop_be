package com.linhpete.java6.presentation.request_handler.mapper;

import com.linhpete.java6.presentation.request_handler.dto.request.OrderDetailRequest;
import com.linhpete.java6.presentation.request_handler.dto.response.OrderDetailResponse;
import com.linhpete.java6.persistence.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface OrderDetailMapper extends AbstractMapper<OrderDetail, OrderDetailRequest, OrderDetailResponse> {
}
