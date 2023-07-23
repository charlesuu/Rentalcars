package com.example.rentalcars.domain.user.loanhistory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

    boolean existsByCarNameAndIsReturn(String name, boolean isReturn);
}
