package com.program.springpost.resources;

import com.program.springpost.domain.Post;
import com.program.springpost.dto.PostDto;
import com.program.springpost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<PostDto> findById(@PathVariable String id) {
        Post post = service.findById(id);
        return ResponseEntity.ok().body(new PostDto(post));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/titlesearch")
    public ResponseEntity<List<PostDto>> findByTitle(@RequestParam(value = "title", defaultValue = "") String text) {
        text = URLDecoder.decode(text, StandardCharsets.UTF_8);

        List<Post> list = service.findByTitleContaining(text);

        List<PostDto> listDto = list.stream()
                .map(post -> new PostDto(post))
                .toList();

        return ResponseEntity.ok().body(listDto);
    }

}
