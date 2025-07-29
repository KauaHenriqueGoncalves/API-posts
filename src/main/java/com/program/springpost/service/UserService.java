package com.program.springpost.service;

import com.program.springpost.domain.User;
import com.program.springpost.repository.UserRepository;

import com.program.springpost.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        List<User> list = repository.findAll();
        return list;
    }

    public User findById(String id){
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> {
            throw new ObjectNotFoundException("User with id " + id + " not found");
        });
    }

}
