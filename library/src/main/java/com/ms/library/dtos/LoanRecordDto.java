package com.ms.library.dtos;

import com.ms.library.enums.StatusLoan;
import com.ms.library.models.BookModel;
import com.ms.library.models.UserModel;

import java.time.LocalDateTime;


public record LoanRecordDto(UserModel userModel,
                            Integer bookQuantity,
                            BookModel bookModel,
                            LocalDateTime returnDate,
                            StatusLoan status
                           )
{
}
