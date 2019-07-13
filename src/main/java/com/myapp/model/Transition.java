package com.myapp.model;

import java.util.Objects;

public class Transition {
    private State from;
    private Action action;

    public Transition(State from, Action action) {
        this.from = from;
        this.action = action;
    }

    public State getFrom() {
        return from;
    }

    public void setFrom(State from) {
        this.from = from;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transition that = (Transition) o;
        return from == that.from &&
                action == that.action;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, action);
    }
}
