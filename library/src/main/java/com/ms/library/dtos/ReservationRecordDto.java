package com.ms.library.dtos;

import com.ms.library.enums.StatusReservation;
import com.ms.library.models.BookModel;
import com.ms.library.models.UserModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record ReservationRecordDto(LocalDateTime reservationDate,
                                   LocalDateTime returnDate,
                                   StatusReservation status,
                                   UserModel userModel,
                                   BookModel bookModel)
{
}


