package com.example.rentalcars.service.user;


import com.example.rentalcars.domain.user.UserJdbcRepository;
import com.example.rentalcars.dto.user.request.UserCreateRequest;
import com.example.rentalcars.dto.user.request.UserUpdateRequest;
import com.example.rentalcars.dto.user.response.UserResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceForJdbc implements UserService {

    UserJdbcRepository userJdbcRepository;

    @Transactional
    public Long saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(request.getName(), request.getAge());
        return 0L;//serviceV1쓰게 되면 생성된 유저 아이디 반환하도록 변경할 것.(안 해도 기능에는 문제 없음)
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userJdbcRepository.getUserResponses();
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        if (userJdbcRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        userJdbcRepository.updateUserName(request.getName(), request.getId());
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        if (userJdbcRepository.isUserNotExist(id)) {
            throw new IllegalArgumentException();
        }

        userJdbcRepository.deleteUserById(id);
    }
}
