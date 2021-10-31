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

    // 갑자기 doHandle 은 뭐지........하..... handlerMap 은 어디서 활용 하는 거지
    @Override
    public void doHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) {
        final String path = request.getRequestURI();
        System.out.println("path = " + path);

        HttpMethod httpMethod = HttpMethod.valueOf(request.getMethod());

        // get /uri -> request key 생성
        final RequestKey key = new RequestKey(path, httpMethod);

        // handlerMap 에 get key 를  해서 RequestHandler 가져오기
        // handler 를 구현하고 있는 클래스가 없는데?
        final RequestHandler handler = handlerMap.get(key);

        final HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        // response 하기 위해 필요하므로 HttpServletResponse 를 이용한다.
        final MiniResponse miniResponse = new MiniResponse(responseWrapper);

        // context 가 왜 필요한 거지? response 를 내려주는?
        final Context context = new MiniContext(miniResponse);

        // 아니 handler 를 구현한 클래스가 없는데 이건 어디서 실행 되고 있는 건데
        handler.handle(context);    // handle 에 context == response 를 파라미터로 전달 했는데 뭘 하는 거지

        // _handled = false -> 디폴트가 false 인데, 파라미터로 전달 되는 boolean 값으로 세팅하겠다.
        // handler 가 null 이 아니니까 항상 true 인데 true 로 세팅 해놓는 게 무슨 의민데 ㅠㅜ.................
        // handle 이 뭘까........하.....
        // 모든 제어를 (예: 스프링) 프레임워크에 넘기는 거. 에러 페이지 떠올려라.
        baseRequest.setHandled(true);   // 내 핸들러를 was 에 등록해 놓는 행위

    }
}
