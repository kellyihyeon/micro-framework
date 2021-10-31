package com.github.kelly;

@FunctionalInterface
public interface RequestHandler {

    void handle(Context context);

}
