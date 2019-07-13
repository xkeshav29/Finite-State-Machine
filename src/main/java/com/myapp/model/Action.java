package com.myapp.model;

public enum Action {
    FULFIL_ORDER("start"),
    MAKE_PROGRESS("make_progress"),
    CANCEL("cancelled"),
    ACTIVATE("activate"),
    COMPLETE("complete");
    private String state;

    Action(String state) {
        this.state = state;
    }

    private String getAction() {
        return state;
    }

}
