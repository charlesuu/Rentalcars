package com.example.rentalcars.domain.car;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByName(String carName);
}
