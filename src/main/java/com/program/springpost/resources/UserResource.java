package com.program.springpost.resources;

import com.program.springpost.domain.User;
import com.program.springpost.dto.UserDto;
import com.program.springpost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> users = service.findAll();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDto> userDtos = users.stream()
                .map(user -> new UserDto(user)).
                collect(Collectors.toList());

        return ResponseEntity.ok().body(userDtos);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        User user = service.findById(id);
        UserDto userDto = new UserDto(user);
        return ResponseEntity.ok().body(userDto);
    }

}
