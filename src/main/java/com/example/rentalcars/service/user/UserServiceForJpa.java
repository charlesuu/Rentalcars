package com.example.rentalcars.service.user;

import com.example.rentalcars.domain.user.User;
import com.example.rentalcars.domain.user.UserRepository;
import com.example.rentalcars.dto.user.request.UserCreateRequest;
import com.example.rentalcars.dto.user.request.UserUpdateRequest;
import com.example.rentalcars.dto.user.response.UserResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
public class UserServiceForJpa implements UserService {

    private final UserRepository userRepository;

    public UserServiceForJpa(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Long saveUser(UserCreateRequest request) {
        return userRepository.save(new User(request.getName(), request.getAge())).getId();
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName());

        //save생략 (영속성 컨택스트)
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);
    }
}





















