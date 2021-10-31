import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.session.SessionHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Handler extends SessionHandler {

    @Override
    public void doHandle(
            String target,
            Request baseRequest,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {
        final String uri = request.getRequestURI();
        System.out.println("uri = " + uri);
        baseRequest.setHandled(true);
    }
}
