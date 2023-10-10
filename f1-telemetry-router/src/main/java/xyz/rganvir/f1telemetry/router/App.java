package xyz.rganvir.f1telemetry.router;

public class App {
    private static final int FEED_PORT = 20777;
    private static final int MAX_PACKET_SIZE = 4096;
    private static final String KAFKA_SERVER_KEY = "KAFKA_BOOTSTRAP_SERVER";

    public static void main(String[] args) {
        Publisher publisher = new Publisher(System.getProperty(KAFKA_SERVER_KEY));
        FeedListener feedListener = new FeedListener(FEED_PORT, MAX_PACKET_SIZE, publisher);
        new Thread(feedListener::start).start();
        System.out.println("Message router listening to feed");

        Runtime.getRuntime().addShutdownHook(new Thread(feedListener::stop));
        Runtime.getRuntime().addShutdownHook(new Thread(publisher::stop));
    }
}
