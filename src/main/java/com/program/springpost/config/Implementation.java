package com.program.springpost.config;

import com.program.springpost.domain.Post;
import com.program.springpost.domain.User;
import com.program.springpost.dto.AuthorDto;
import com.program.springpost.dto.CommentDto;
import com.program.springpost.repository.PostRepository;
import com.program.springpost.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class Implementation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("25/05/2023"), "Ferias Check", "Estou de ferias pessoal!!!", new AuthorDto(maria));
        Post post2 = new Post(null, sdf.parse("08/03/2020"), "I need a job", "Pleasseee people, i need a job", new AuthorDto(alex));

        post1.getComments().addAll(Arrays.asList(
                new CommentDto("Nego feio do caramba", sdf.parse("30/05/2023"), new AuthorDto(alex))
        ));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().add(post1);
        alex.getPosts().add(post2);

        userRepository.saveAll(Arrays.asList(maria, alex));
    }

}
