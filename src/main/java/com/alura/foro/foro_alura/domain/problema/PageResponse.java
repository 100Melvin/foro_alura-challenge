package com.alura.foro.foro_alura.domain.problema;

import java.util.List;

public record PageResponse<T> (
    List<T> content,
    int pageNumber,
    int totalPages,
    long totalElements
)
    {}

