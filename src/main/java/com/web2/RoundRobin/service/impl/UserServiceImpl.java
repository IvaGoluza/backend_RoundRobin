package com.web2.RoundRobin.service.impl;

import com.web2.RoundRobin.model.DTO.UserLoginDTO;
import com.web2.RoundRobin.model.DTO.UserResponseDTO;
import com.web2.RoundRobin.model.User;
import com.web2.RoundRobin.repository.UserRepository;
import com.web2.RoundRobin.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper modelMapper) {
        this.userRepository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        User user = userRepository.getByEmail(userLoginDTO.getEmail());

        if (user == null) {
            user = modelMapper.map(userLoginDTO, User.class);
            user = userRepository.save(user);
        }

        return modelMapper.map(user, UserResponseDTO.class);
    }


}
