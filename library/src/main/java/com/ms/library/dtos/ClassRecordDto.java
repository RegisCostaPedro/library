package com.ms.library.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClassRecordDto( Integer numClass,
                              String courseName) {
}
