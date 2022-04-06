package com.sample.kafka_stream_demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import com.sample.config.DroolsBeanFactory;
import com.sample.model.Fact;
import com.sample.model.Result;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateTest {
    private Result result;
    private KieSession ksession;
 
    @BeforeEach
    public void before() {
        result = new Result();
        ksession = new DroolsBeanFactory().getKieSession();
    }

    @Test
    public void run() {

        /*
        ksession.setGlobal("result", result);
        ksession.insert(new Fact("Asia", "Planet Earth"));
        ksession.insert(new Fact("China", "Asia"));
        ksession.insert(new Fact("Great Wall of China", "China"));

        ksession.fireAllRules();
        
        // Assert Decision one
        assertEquals(result.getValue(), "Decision one taken: Great Wall of China BELONGS TO Planet Earth");
        */
    }
}
