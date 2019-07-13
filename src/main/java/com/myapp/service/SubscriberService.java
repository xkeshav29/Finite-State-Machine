package com.myapp.service;

import com.myapp.model.StateMachine;
import com.myapp.model.Subscriber;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubscriberService {

    public void registerSubscribers(List<Subscriber> subscribers) {
        subscribers.forEach(StateMachine::registerSubscriber);
    }

    public void deregisterSubscriber(Subscriber subscriber) {
        StateMachine.deregisterSubscriber(subscriber);
    }
}
