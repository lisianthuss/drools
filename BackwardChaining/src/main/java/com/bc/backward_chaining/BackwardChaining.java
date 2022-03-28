package com.bc.backward_chaining;

import org.kie.api.runtime.KieSession;

import com.bc.config.DroolsBeanFactory;
import com.bc.model.Fact;
import com.bc.model.Result;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

public class BackwardChaining {
    public static void main(String[] args) {
        Result result = new BackwardChaining().backwardChaining();
        System.out.println(result.getValue());
        result.getFacts()
            .stream()
            .forEach(System.out::println);
    }

    public Result backwardChaining() {
        Result result = new Result();
        KieSession ksession = new DroolsBeanFactory().getKieSession();
        ksession.setGlobal("result", result);
        ksession.insert(new Fact("Asia", "Planet Earth"));
        ksession.insert(new Fact("China", "Asia"));
        ksession.insert(new Fact("Great Wall of China", "China"));

        ksession.fireAllRules();

        return result;
    }
}
