package com.github.kelly;

// wrapper
public final class Mini {

    private Mini() {
        // prevent to instantiate
    }

    private static class SingletonHolder {
        private static final MiniEngine INSTANCE = new MiniEngine();
    }

    private static MiniEngine getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void get(String path, RequestHandler requestHandler) {
        getInstance().addHandler(path, requestHandler, HttpMethod.GET);
    }

    public static void port(int port) {
        getInstance().configurePort(port);
    }

    public static void run() {
        getInstance().start();
    }
}
