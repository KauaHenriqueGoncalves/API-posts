package com.program.springpost.dto;

import com.program.springpost.domain.Post;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class PostDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private Date date;
    private String title;
    private String content;
    private AuthorDto author;

    public PostDto() {
    }

    public PostDto(Post post) {
        BeanUtils.copyProperties(post, this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }
}
