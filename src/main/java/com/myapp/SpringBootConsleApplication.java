package com.myapp;

import com.myapp.model.*;
import com.myapp.service.ConsumerService;
import com.myapp.service.ProducerService;
import com.myapp.service.SubscriberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static com.myapp.model.State.*;


@SpringBootApplication
public class SpringBootConsleApplication
        implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(SpringBootConsleApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(SpringBootConsleApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    private static List<Action> actions = Arrays.asList(Action.FULFIL_ORDER,
            Action.COMPLETE,
            Action.CANCEL);

    private static List<Subscriber> subscribers = Arrays.asList(new SubscriberImpl1("Sub1"),
            new SubscriberImpl1("Sub2"),
            new SubscriberImpl1("Sub3"));

    private static Map<Transition, State> validTransitions = new HashMap<>();

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private SubscriberService subscriberService;

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
        initValidTransitions();
        StateMachine.init(validTransitions);
        subscriberService.registerSubscribers(subscribers);
        BlockingQueue<Action> eventQueue = new LinkedBlockingDeque<>();
        actions.forEach(action -> producerService.produce(eventQueue, action));
        consumerService.consume(eventQueue);
    }

    private void initValidTransitions() {
        validTransitions.put(new Transition(State.START, Action.FULFIL_ORDER), CREATED);
        validTransitions.put(new Transition(CREATED, Action.ACTIVATE), ACTIVATED);
        validTransitions.put(new Transition(CREATED, Action.CANCEL), CANCELLED);
        validTransitions.put(new Transition(State.ACTIVATED, Action.MAKE_PROGRESS), INPROGRESS);
        validTransitions.put(new Transition(State.ACTIVATED, Action.CANCEL), CANCELLED);
        validTransitions.put(new Transition(State.INPROGRESS, Action.CANCEL), CANCELLED);
        validTransitions.put(new Transition(State.INPROGRESS, Action.COMPLETE), COMPLETED);
    }
}