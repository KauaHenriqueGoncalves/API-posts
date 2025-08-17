package com.program.springpost;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringPostApplication {

    public static void main(String[] args) {
//        Dotenv dotenv = Dotenv.load();
//        System.setProperty("MONGO_IP", dotenv.get("MONGO_IP"));
//        System.setProperty("MONGO_PORT", dotenv.get("MONGO_PORT"));
//        System.setProperty("MONGO_NAME_DB", dotenv.get("MONGO_NAME_DB"));

        SpringApplication.run(SpringPostApplication.class, args);
        System.out.println("Local host: http://localhost:8080/");
    }

}
