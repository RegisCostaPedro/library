package com.ms.library.dtos;

import com.ms.library.enums.StatusLoan;
import com.ms.library.models.BookModel;
import com.ms.library.models.UserModel;

import java.time.LocalDateTime;
import java.util.Date;

public record LoanRecordDto(UserModel userModel,
                            BookModel bookModel,
                            LocalDateTime returnDate
                           )
{
}
