package com.example;

import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.api.runtime.KieSession;

import com.sample.config.DroolsBeanFactory;
import com.sample.model.Fact;
import com.sample.model.Result;

import com.example.PostsRepository;
import com.example.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

public class App {
    @Autowired
    PostsRepository postsRepository;

    public static void main(String[] args) {
        Result result = new App().run();
        System.out.println(result.getValue());
        result.getFacts()
            .stream()
            .forEach(System.out::println);
    }

    public Result run() {
        Result result = new Result();
        /*
        KieSession ksession = new DroolsBeanFactory().getKieSession();
        ksession.setGlobal("result", result);
        ksession.insert(new Fact("Joy", " "));

        ksession.fireAllRules();
         */
        List<Posts> postList = postsRepository.findAll();

        Posts posts;
        posts = postList.get(0);

        return result;
    }
}
