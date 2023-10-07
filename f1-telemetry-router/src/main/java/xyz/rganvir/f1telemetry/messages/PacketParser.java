package xyz.rganvir.f1telemetry.messages;

import org.assertj.core.util.VisibleForTesting;

import java.util.Arrays;
import java.util.List;

public enum PacketParser {
    ;

    @VisibleForTesting
    public static final List<Object> SUPPORTED_HEADER_IDS = Arrays.asList(0, 1, 2, 6, 10, 11, 12);

    public static GameMessage parse(byte[] rawData) {
        ByteBufferParser byteParser = new ByteBufferParser(rawData);
        PacketHeader header = getPacketHeader(byteParser);

        if (!SUPPORTED_HEADER_IDS.contains(header.packetId())) {
            return null;
        }

        return switch(header.packetId()) {
            case 0 -> GameMessageFactory.motionMessage(byteParser);
            case 1 -> GameMessageFactory.carStatusMessage(byteParser);
            case 2 -> GameMessageFactory.lapDataMessage(byteParser);
            case 6 -> GameMessageFactory.carTelemetryMessage(byteParser);
            case 10 -> GameMessageFactory.carDamagePacket(byteParser);
            case 11 -> GameMessageFactory.sessionHistoryMessage(byteParser);
            case 12 -> GameMessageFactory.tyreSetsMessage(byteParser);
            default -> throw new IllegalStateException("PacketHeaderId not supported: " + header.packetId());
        };
    }

    @VisibleForTesting
    public static PacketHeader getPacketHeader(ByteBufferParser buffer) {
        return new PacketHeader(
                buffer.getUint16(),
                buffer.getUint8(),
                buffer.getUint8(),
                buffer.getUint8(),
                buffer.getUint8(),
                buffer.getUint8(),
                buffer.getLong(),
                buffer.getFloat(),
                buffer.getUint32(),
                buffer.getUint32(),
                buffer.getUint8(),
                buffer.getUint8()
        );
    }

}
