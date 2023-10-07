package xyz.rganvir.f1telemetry;

import xyz.rganvir.f1telemetry.messages.GameMessage;
import xyz.rganvir.f1telemetry.messages.PacketParser;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class FeedListener {
  private final int port;
  private final int maxPacketSize;
  private volatile boolean shutdown;

  public FeedListener(int port, int maxPacketSize) {
    this.port = port;
    this.maxPacketSize = maxPacketSize;
    this.shutdown = false;
  }

  public void start() {
    byte[] buffer = new byte[this.maxPacketSize];
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

    try (DatagramSocket socket = new DatagramSocket(port)) {
      while (!shutdown) {
        socket.receive(packet);

        GameMessage message = PacketParser.parse(buffer);
        if (message != null) {

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
