package com.myapp.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StateMachine {

    private static volatile StateMachine instance;

    private static State currentState;

    private static Set<Subscriber> subscribers = new HashSet<>();

    private static Map<Transition, State> validTransitions;

    private static Logger LOG = LoggerFactory
            .getLogger(StateMachine.class);

    private StateMachine(State state) {
        currentState = state;
    }

    public synchronized static void init(Map<Transition, State> transitionStateMap) {
        if(instance == null) {
            synchronized (StateMachine.class) {
                instance = new StateMachine(State.START);
                validTransitions = transitionStateMap;
                LOG.info("State Machine Initialized");
            }
        }
        else
            throw new RuntimeException("Already initialized");
    }

    public synchronized static void processEvent(Action action) {
        synchronized(StateMachine.class) {
            Transition transition = new Transition(currentState, action);
            LOG.info("Processing transition for {} at {}", action, currentState);
            State previousState = currentState;
            if (validTransitions.containsKey(transition)) {
                currentState = validTransitions.get(transition);
                Event event = new Event(previousState, action, currentState);
                LOG.info("Transition successful from:{} action:{} to:{}", previousState, action, currentState);
                subscribers.forEach(subscriber -> subscriber.eventReported(event));
            } else
                LOG.info("Dropping Action : {} Invalid transition requested from {}.", action.toString(), previousState);
        }
    }

    public synchronized static void registerSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public synchronized static void deregisterSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }


}
