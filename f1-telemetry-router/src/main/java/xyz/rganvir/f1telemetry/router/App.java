package xyz.rganvir.f1telemetry.router;

public class App {
    private static final int FEED_PORT = 20777;
    private static final int MAX_PACKET_SIZE = 4096;

    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        FeedListener feedListener = new FeedListener(FEED_PORT, MAX_PACKET_SIZE, publisher);
        feedListener.start();
        Runtime.getRuntime().addShutdownHook(new Thread(feedListener::stop));
    }
}
