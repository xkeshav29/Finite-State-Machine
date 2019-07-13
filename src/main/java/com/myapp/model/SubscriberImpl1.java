package com.myapp.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriberImpl1 implements Subscriber {

    private String name;

    private static Logger LOG = LoggerFactory
            .getLogger(SubscriberImpl1.class);

    public SubscriberImpl1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void eventReported(Event event) {
        LOG.info("Event {} reported by Subscriber {}", event.toString(), name);
    }

}
