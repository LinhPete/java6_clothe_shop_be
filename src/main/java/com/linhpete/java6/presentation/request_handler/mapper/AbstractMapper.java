package com.linhpete.java6.presentation.request_handler.mapper;

import org.mapstruct.MappingTarget;

public interface AbstractMapper<E, Q, S> {
    E mapFromRequest(Q productRequest);
    void updateEntity(@MappingTarget E product, Q request);
    S mapToResponse(E product);
}
