import io.grpc.BindableService;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class SignUpServer {
    private static final int PORT = 50051;
    private io.grpc.Server server;

    public void start() throws IOException {
        server = ServerBuilder.forPort(PORT)
                .addService((BindableService) new SignUpServerImpl())
                .build()
                .start();
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server == null) {
            return;
        }

        server.awaitTermination();
    }

    //서버 구동
    public static void main(String[] args)
            throws InterruptedException, IOException {
            SignUpServer server = new SignUpServer();
            server.start();
            server.blockUntilShutdown();
    }
}