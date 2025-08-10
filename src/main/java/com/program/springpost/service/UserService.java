package com.program.springpost.service;

import com.program.springpost.domain.Post;
import com.program.springpost.domain.User;
import com.program.springpost.dto.UserDto;
import com.program.springpost.repository.UserRepository;

import com.program.springpost.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public User create(User obj){
        User user = new User(null, obj.getName(), obj.getEmail());
        return repository.save(user);
    }

    public User update(String id, User obj){
        User user = findById(id);
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        return repository.save(user);
    }

    public void deleteById(String id){
        Optional<User> user = repository.findById(id);
        if (user.isPresent()){
            repository.deleteById(id);
        }
        else {
            throw new ObjectNotFoundException("User with id " + id + " not found");
        }
    }

    public List<Post> findPosts(String id){
        User user = findById(id);

        List<Post> posts = new ArrayList<>(user.getPosts());

        return posts;
    }

}
