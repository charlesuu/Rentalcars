package com.example.rentalcars.domain.user;

import com.example.rentalcars.domain.user.loanhistory.UserRentalHistory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false)
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRentalHistory> userLoanHistories = new ArrayList<>();

    protected User() {
    }

    public User(String name, Integer age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘 못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }


    public void rentCar(String carName) {
        this.userLoanHistories.add(new UserRentalHistory(this, carName));
    }

    public void returnCar(String carName) {
        //이름으로 렌트 기록 찾기
        UserRentalHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getCarName().equals(carName))
                .filter(history -> !history.isReturn())
                .findFirst()//Optional로 반환해주는 함수
                .orElseThrow(IllegalArgumentException::new);
        //반납 처리
        targetHistory.doReturn();
    }


}
