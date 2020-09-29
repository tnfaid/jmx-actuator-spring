package com.example.demo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/titik/api")
@ManagedResource(objectName="TitiksMBeans:category=MBeans,name=testBean")
public class MyRestController {
    final Counter titiks_greeting;
    public MyRestController(MeterRegistry registry){
        titiks_greeting = registry.counter("titiksgreeting");
    }

    @GetMapping("/greeting")
    @ManagedOperation
    public String greeting() throws InterruptedException {
        titiks_greeting.increment();
        return "hello... :-)";
    }


    @ManagedOperation
    @ManagedOperationParameters()
    public String greetingWithName(String name) throws InterruptedException {
                titiks_greeting.increment();
        return "hello... :-) " + name;
    }
    @ManagedOperation
    public String greetingsReturnCount() throws InterruptedException {
        titiks_greeting.increment();
        return "hello... :-) " + titiks_greeting.count();
    }

}
