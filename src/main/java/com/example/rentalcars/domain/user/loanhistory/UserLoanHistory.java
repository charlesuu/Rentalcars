package com.example.rentalcars.domain.user.loanhistory;

import com.example.rentalcars.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String carName;

    private boolean isReturn;

    protected UserLoanHistory() {
    }

    public UserLoanHistory(User user, String carName) {
        this.user = user;
        this.carName = carName;
        this.isReturn = false;
    }

    public void doReturn() {
        this.isReturn = true;
    }

    public String getCarName() {
        return this.carName;
    }

    public boolean isReturn() {
        return isReturn;
    }
}
