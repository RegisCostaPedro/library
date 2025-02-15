package com.ms.library.dtos;

import com.ms.library.enums.StatusReservation;
import com.ms.library.models.BookModel;
import com.ms.library.models.UserModel;

import java.util.Date;

public record ReservationRecordDto(Date reservationDate,
                                   StatusReservation status,
                                   UserModel userModel,
                                   BookModel bookModel)
{
}


