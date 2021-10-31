package com.github.kelly;

public class RequestKey {

    private final String path;
    private final HttpMethod method;

    public RequestKey(String path, HttpMethod method) {
        this.path = path;
        this.method = method;
    }

    // 왜 해야 되는지?
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
