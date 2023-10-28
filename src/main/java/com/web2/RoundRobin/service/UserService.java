package com.web2.RoundRobin.service;

import com.web2.RoundRobin.model.DTO.UserLoginDTO;
import com.web2.RoundRobin.model.DTO.UserResponseDTO;

public interface UserService {

    UserResponseDTO loginUser(UserLoginDTO userLoginDTO);

}
