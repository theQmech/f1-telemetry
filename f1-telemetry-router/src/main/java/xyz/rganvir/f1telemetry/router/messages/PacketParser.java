package xyz.rganvir.f1telemetry.router.messages;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

public enum PacketParser {
    ;

    public static List<GameMessage> parse(byte[] rawData, int length) {
        ArrayList<GameMessage> messages = new ArrayList<>();

        ByteBufferParser byteParser = new ByteBufferParser(rawData, 0, length);
        while (byteParser.hasRemaining()) {
            GameMessage singleMessage = getSingleMessage(byteParser);
            messages.add(singleMessage);
        }
        return messages;
    }

    // returns null if packet not supported.
    private static GameMessage getSingleMessage(ByteBufferParser byteParser) {
        PacketHeader header = getPacketHeader(byteParser);

        System.out.printf("Parsing for packetId [%d] remaining [%d]B%n", header.packetId(), byteParser.remaining());

        return switch (header.packetId()) {
            case 0 -> GameMessageFactory.motionMessage(byteParser);
            case 1 -> GameMessageFactory.sessionMessage(byteParser);
            case 2 -> GameMessageFactory.lapDataMessage(byteParser);
            case 3 -> GameMessageFactory.eventMessage(byteParser);
            case 4 -> GameMessageFactory.participantsMessage(byteParser);
            case 5 -> GameMessageFactory.carSetups(byteParser);
            case 6 -> GameMessageFactory.carTelemetryMessage(byteParser);
            case 7 -> GameMessageFactory.carStatusMessage(byteParser);
            case 8 -> GameMessageFactory.finalClassification(byteParser);
            case 9 -> GameMessageFactory.lobbyInfo(byteParser);
            case 10 -> GameMessageFactory.carDamagePacket(byteParser);
            case 11 -> GameMessageFactory.sessionHistoryMessage(byteParser);
            case 12 -> GameMessageFactory.tyreSetsMessage(byteParser);
            case 13 -> GameMessageFactory.motionExtendedMessage(byteParser);
            default -> throw new IllegalStateException("PacketHeaderId not supported: " + header.packetId());
        };
    }

    @VisibleForTesting
    public static PacketHeader getPacketHeader(ByteBufferParser buffer) {
        return new PacketHeader(buffer.getUint16(), buffer.getUint8(), buffer.getUint8(), buffer.getUint8(), buffer.getUint8(), buffer.getUint8(), buffer.getLong(), buffer.getFloat(), buffer.getUint32(), buffer.getUint32(), buffer.getUint8(), buffer.getUint8());
    }
}
