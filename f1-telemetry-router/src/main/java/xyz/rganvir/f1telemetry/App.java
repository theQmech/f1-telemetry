package xyz.rganvir.f1telemetry;

public class App {
    private static final int FEED_PORT = 20777;
    private static final int MAX_PACKET_SIZE = 4096;

    public static void main(String[] args) {
        FeedListener feedListener = new FeedListener(FEED_PORT, MAX_PACKET_SIZE);
        feedListener.start();
        System.out.println( "Hello World!" );
    }
}
