package com.myapp.service;

import com.myapp.model.Action;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class ProducerService {

    public void produce(BlockingQueue<Action> queue, Action action) {
        queue.add(action);
    }
}
