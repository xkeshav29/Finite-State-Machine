package com.myapp.model;

public class Event {
    private State fromState;
    private Action action;
    private State toState;

    public Event(State fromState, Action action, State toState) {
        this.fromState = fromState;
        this.action = action;
        this.toState = toState;
    }

    public State getFromState() {
        return fromState;
    }

    public void setFromState(State fromState) {
        this.fromState = fromState;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public State getToState() {
        return toState;
    }

    public void setToState(State toState) {
        this.toState = toState;
    }

    @Override
    public String toString() {
        return "Event{" +
                "fromState=" + fromState +
                ", action=" + action +
                ", toState=" + toState +
                '}';
    }
}
