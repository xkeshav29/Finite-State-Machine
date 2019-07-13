package com.myapp.service;

import com.myapp.model.Action;
import com.myapp.model.StateMachine;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class ConsumerService {

    private Executor threadPool = Executors.newFixedThreadPool(5);

    public void consume(BlockingQueue<Action> queue) {
        while (true) {
            if(!queue.isEmpty())
                threadPool.execute( () -> {
                    try {
                        StateMachine.processEvent(queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        }
    }

}
