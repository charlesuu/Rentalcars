package com.example.rentalcars.service.car;

import com.example.rentalcars.domain.car.Car;
import com.example.rentalcars.domain.car.CarRepository;
import com.example.rentalcars.domain.user.User;
import com.example.rentalcars.domain.user.UserRepository;
import com.example.rentalcars.domain.user.loanhistory.UserRentalHistoryRepository;
import com.example.rentalcars.dto.car.request.CarCreateRequest;
import com.example.rentalcars.dto.car.request.CarRentalRequest;
import com.example.rentalcars.dto.car.request.CarReturnRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final UserRentalHistoryRepository userRentalHistoryRepository;

    @Transactional
    public void saveCar(CarCreateRequest request) {
        carRepository.save(new Car(request.getName()));
    }

    @Transactional
    public void rentCar(CarRentalRequest request) {
        //1. Car객체를 가져온다. optional을 통해 존재하지 않는다면 예외를 던진다.
        Car car = carRepository.findByName(request.getCarName())
                .orElseThrow(IllegalArgumentException::new);

        //2. 렌트 이력이 있는 책인지 확인. 만약 (렌트 이력이 있고 && 반납되지 않은 차)라면 예외를 던진다.
        if (userRentalHistoryRepository.existsByCarNameAndIsReturn(car.getName(), false)) {
            throw new IllegalArgumentException("이미 렌트된 차입니다.");
        }

        //3. 빌릴 유저 객체를 가져온다. Car조회 때와 마찬가지로 없다면 예외를 던진다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        //4. user 객체가 책을 대출한다.
        user.rentCar(car.getName());
    }

    @Transactional
    public void returnCar(CarReturnRequest request) {
        //이름으로 user객체를 가저온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        user.returnCar(request.getCarName());
    }
}
































