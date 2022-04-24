package com.github.kelly;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.session.SessionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.Map;

// Handler 의 목적은? 존재 이유는?
// 매칭해주는 안내자 역할?

// Spring 의 DispatcherServlet 모사한 모듈
public class HttpHandler extends SessionHandler {

    private final Map<RequestKey, RequestHandler> handlerMap;

    public HttpHandler(Map<RequestKey, RequestHandler> handlerMap) {
        this.handlerMap = handlerMap;
    }


    @Override
    public void doHandle(String target,
                         Request baseRequest,
                         HttpServletRequest request,
                         HttpServletResponse response)
    {

        final String path = request.getRequestURI();
        final HttpMethod httpMethod = HttpMethod.valueOf(request.getMethod());
        final RequestKey key = new RequestKey(path, httpMethod);
        System.out.println("key = " + key);

        // handler = void handle(Context context) {
                        // ctx.response().text("Hello, Framework!");}
        final RequestHandler handler = handlerMap.get(key);

        final HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        // response 하기 위해 필요하므로 HttpServletResponse 를 이용한다.
        final MiniResponse miniResponse = new MiniResponse(responseWrapper);

        final Context context = new MiniContext(miniResponse);

        handler.handle(context);

        // 모든 제어를 (예: 스프링) 프레임워크에 넘기는 거. 에러 페이지 떠올려라.
        // 내 핸들러를 was 에 등록
        baseRequest.setHandled(true);

    }
}
