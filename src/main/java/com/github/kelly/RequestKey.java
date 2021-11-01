package com.github.kelly;

import java.util.Objects;

public class RequestKey {

    private final String path;
    private final HttpMethod method;

    public RequestKey(String path, HttpMethod method) {
        this.path = path;
        this.method = method;
    }

    // 왜 해야 되는지?
    // 정의를 어떻게 하느냐에 따라 response 200 ok 거나 500 에러 뜸
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestKey that = (RequestKey) o;
        return Objects.equals(path, that.path) && method == that.method;
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, method);
    }

    @Override
    public String toString() {
        return "RequestKey{" +
                "path='" + path + '\'' +
                ", method=" + method +
                '}';
    }
}
