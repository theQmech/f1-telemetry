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
            case 0 -> GameMessageFactory.motionMessage(header, byteParser);
            case 1 -> GameMessageFactory.sessionMessage(header, byteParser);
            case 2 -> GameMessageFactory.lapDataMessage(header, byteParser);
            case 3 -> GameMessageFactory.eventMessage(header, byteParser);
            case 4 -> GameMessageFactory.participantsMessage(header, byteParser);
            case 5 -> GameMessageFactory.carSetups(header, byteParser);
            case 6 -> GameMessageFactory.carTelemetryMessage(header, byteParser);
            case 7 -> GameMessageFactory.carStatusMessage(header, byteParser);
            case 8 -> GameMessageFactory.finalClassification(header, byteParser);
            case 9 -> GameMessageFactory.lobbyInfo(header, byteParser);
            case 10 -> GameMessageFactory.carDamagePacket(header, byteParser);
            case 11 -> GameMessageFactory.sessionHistoryMessage(header, byteParser);
            case 12 -> GameMessageFactory.tyreSetsMessage(header, byteParser);
            case 13 -> GameMessageFactory.motionExtendedMessage(header, byteParser);
            default -> throw new IllegalStateException("PacketHeaderId not supported: " + header.packetId());
        };
    }

    @VisibleForTesting
    public static PacketHeader getPacketHeader(ByteBufferParser buffer) {
        return new PacketHeader(buffer.getUint16(), buffer.getUint8(), buffer.getUint8(), buffer.getUint8(), buffer.getUint8(), buffer.getUint8(), buffer.getLong(), buffer.getFloat(), buffer.getUint32(), buffer.getUint32(), buffer.getUint8(), buffer.getUint8());
    }
}
