package com.example.rentalcars.controller.car;

import com.example.rentalcars.dto.car.request.CarCreateRequest;
import com.example.rentalcars.dto.car.request.CarRentalRequest;
import com.example.rentalcars.dto.car.request.CarReturnRequest;
import com.example.rentalcars.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping("/car")
    public void saveCar(@RequestBody CarCreateRequest request) {
        carService.saveCar(request);
    }

    @PostMapping("/car/rental")
    public void rentCar(@RequestBody CarRentalRequest request) {
        carService.rentCar(request);
    }

    @PutMapping("/car/return")
    public void returnCar(@RequestBody CarReturnRequest request) {
        carService.returnCar(request);
    }

}
