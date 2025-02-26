package com.ms.library.repositories;

import com.ms.library.enums.StatusLoan;
import com.ms.library.models.LoanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<LoanModel, UUID> {
    @NativeQuery("SELECT * FROM tb_loan l JOIN tb_book b on l.id_book = b.book_id WHERE b.book_id = :bookId AND l.status = 0")
    List<LoanModel> checkBookStatus(@Param("bookId") UUID bookId);


    @NativeQuery("SELECT COUNT(*) FROM public.tb_loan join tb_book on tb_book.book_id = tb_loan.id_book join tb_user on tb_user.user_id = tb_loan.user_id where tb_user.user_id = :userId ")
    Long countLoans(UUID userId);

}
