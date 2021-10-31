package com.github.kelly;

public class MiniContext implements Context {

    private final Response response;

    public MiniContext(Response response) {
        this.response = response;
    }

    @Override
    public Response response() {
        return response;
    }
}
