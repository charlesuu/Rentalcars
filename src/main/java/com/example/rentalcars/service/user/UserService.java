package com.example.rentalcars.service.user;

import com.example.rentalcars.dto.user.request.UserCreateRequest;
import com.example.rentalcars.dto.user.request.UserUpdateRequest;
import com.example.rentalcars.dto.user.response.UserResponse;
import java.util.List;

public interface UserService {

    public Long saveUser(UserCreateRequest request);

    public List<UserResponse> getUsers();

    public void updateUser(UserUpdateRequest request);

    public void deleteUser(Long id);
}
