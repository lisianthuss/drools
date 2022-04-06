package com.example.application;

import org.kie.api.runtime.KieSession;

import com.sample.config.DroolsBeanFactory;
import com.sample.model.Fact;
import com.sample.model.Result;

public class App {
    public static void main(String[] args) {
        Result result = new App().run();
        System.out.println(result.getValue());
        result.getFacts()
            .stream()
            .forEach(System.out::println);
    }

    public Result run() {
        Result result = new Result();
        KieSession ksession = new DroolsBeanFactory().getKieSession();
        ksession.setGlobal("result", result);
        ksession.insert(new Fact("Joy", " "));

        ksession.fireAllRules();

        return result;
    }
}
