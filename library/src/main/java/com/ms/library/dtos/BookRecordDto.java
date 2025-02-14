package com.ms.library.dtos;

import java.util.UUID;

public record BookRecordDto(
                            String isbn,
                            String author,
                            String title,
                            String description,
                            Integer quantity_available,
                            String publisher,
                            Integer year_publication,
                            String genre,
                            String image

) {
}
