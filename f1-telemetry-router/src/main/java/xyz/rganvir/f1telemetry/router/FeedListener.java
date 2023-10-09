package xyz.rganvir.f1telemetry.router;

import xyz.rganvir.f1telemetry.router.messages.GameMessage;
import xyz.rganvir.f1telemetry.router.messages.PacketParser;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class FeedListener {
    private final int port;
    private final int maxPacketSize;
    private final Publisher publisher;
    private volatile boolean shutdown;

    public FeedListener(int port, int maxPacketSize, Publisher publisher) {
        this.port = port;
        this.maxPacketSize = maxPacketSize;
        this.publisher = publisher;
        this.shutdown = false;
    }

    public void start() {
        byte[] buffer = new byte[this.maxPacketSize];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        try (DatagramSocket socket = new DatagramSocket(port)) {
            while (!shutdown) {
                socket.receive(packet);

                GameMessage message = PacketParser.parse(buffer);
                System.out.printf("Received message %s%n", message == null ? "NULL" : message.type().name());
                if (message != null) {
                    this.publisher.publish(message);
                }

                packet.setLength(this.maxPacketSize);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        this.shutdown = false;
    }
}
