package com.myapp.model;

public enum State {

    START("start"),
    CREATED("created"),
    CANCELLED("cancelled"),
    ACTIVATED("activated"),
    INPROGRESS("inprogress"),
    COMPLETED("completed"),
    END("end");

    private String state;

    State(String state) {
        this.state = state;
    }

    private String getState() {
        return state;
    }
}
