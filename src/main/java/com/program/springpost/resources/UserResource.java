package com.program.springpost.resources;

import com.program.springpost.domain.Post;
import com.program.springpost.domain.User;
import com.program.springpost.dto.UserDto;
import com.program.springpost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDto> create(@RequestBody User user) {
        user = service.create(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<UserDto> update(@PathVariable String id, @RequestBody User user) {
        user = service.update(id, user);
        UserDto userDto = new UserDto(user);
        return ResponseEntity.ok().body(userDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        List<Post> posts = service.findPosts(id);
        return ResponseEntity.ok().body(posts);
    }

}
