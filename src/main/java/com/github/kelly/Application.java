package com.github.kelly;

public class Application {

    public static void main(String[] args) {
        // get(String path, RequestHandler requestHandler)
                            // void handle(Context context);
        Mini.get("/hello", ctx -> {
            ctx.response().text("Hello, Framework!");
        });

        Mini.port(12345);
        Mini.run();
    }
}
