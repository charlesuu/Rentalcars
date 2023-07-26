package com.example.rentalcars.domain.user.loanhistory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRentalHistoryRepository extends JpaRepository<UserRentalHistory, Long> {

    boolean existsByCarNameAndIsReturn(String name, boolean isReturn);
}
