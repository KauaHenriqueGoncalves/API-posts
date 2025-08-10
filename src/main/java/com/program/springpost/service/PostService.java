package com.program.springpost.service;

import com.program.springpost.domain.Post;
import com.program.springpost.repository.PostRepository;
import com.program.springpost.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id){
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() -> {
            throw  new ObjectNotFoundException("Post not found with id: " + id);
        });
    }

    public List<Post> findByTitleContaining(String title){
        List<Post> list = postRepository.findByTitleContainingIgnoreCase(title);
        return list;
    }

}
