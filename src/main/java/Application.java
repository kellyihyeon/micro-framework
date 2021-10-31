import org.eclipse.jetty.server.Server;

public class Application {

    public static void main(String[] args) throws Exception {
        final Server server = new Server(12345);
        server.setHandler(new Handler());
        server.start();
        server.join();
    }
}
