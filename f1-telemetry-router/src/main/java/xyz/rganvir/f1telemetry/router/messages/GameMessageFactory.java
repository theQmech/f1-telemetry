package xyz.rganvir.f1telemetry.router.messages;

public enum GameMessageFactory {
    ;

    private static final int headerSize = 29;

    public static CarDamageMessage carDamagePacket(PacketHeader header,  ByteBufferParser byteParser) {
        CarDamageMessage.CarDamageData[] data = new CarDamageMessage.CarDamageData[22];
        for (int i = 0; i < 22; ++i) {
            data[i] =
                    new CarDamageMessage.CarDamageData(
                            byteParser.getFloatArray(4),
                            byteParser.getUint8Array(4),
                            byteParser.getUint8Array(4),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8());
        }
        return new CarDamageMessage(header, data);
    }

    public static MotionMessage motionMessage(PacketHeader header, ByteBufferParser byteParser) {
        var data = new MotionMessage.CarMotionData[22];
        for (int i = 0; i < 22; i++) {
            data[i] =
                    new MotionMessage.CarMotionData(
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getInt16(),
                            byteParser.getInt16(),
                            byteParser.getInt16(),
                            byteParser.getInt16(),
                            byteParser.getInt16(),
                            byteParser.getInt16(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getFloat());
        }
        return new MotionMessage(header, data);
    }

    public static SessionHistoryMessage sessionHistoryMessage(PacketHeader header,  ByteBufferParser byteParser) {
        var lapHistoryData = new SessionHistoryMessage.LapHistoryData[100];
        for (int i = 0; i < 100; ++i) {
            lapHistoryData[i] =
                    new SessionHistoryMessage.LapHistoryData(
                            byteParser.getUint32(),
                            byteParser.getUint16(),
                            byteParser.getUint8(),
                            byteParser.getUint16(),
                            byteParser.getUint8(),
                            byteParser.getUint16(),
                            byteParser.getUint8(),
                            byteParser.getUint8());
        }
        var tyreStintHistoryData = new SessionHistoryMessage.TyreStintHistoryData[8];
        for (int i = 0; i < 8; ++i) {
            tyreStintHistoryData[i] =
                    new SessionHistoryMessage.TyreStintHistoryData(
                            byteParser.getUint8(), byteParser.getUint8(), byteParser.getUint8());
        }
        return new SessionHistoryMessage(
                header,
                byteParser.getUint8(),
                byteParser.getUint8(),
                byteParser.getUint8(),
                byteParser.getUint8(),
                byteParser.getUint8(),
                byteParser.getUint8(),
                byteParser.getUint8(),
                lapHistoryData,
                tyreStintHistoryData);
    }

    public static TyreSetsMessage tyreSetsMessage(PacketHeader header, ByteBufferParser byteParser) {
        var tyreSetData = new TyreSetsMessage.TyreSetData[20];
        for (int i = 0; i < 20; ++i) {
            tyreSetData[i] =
                    new TyreSetsMessage.TyreSetData(header,
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getInt16(),
                            byteParser.getUint8());
        }

        return new TyreSetsMessage(byteParser.getUint8(), tyreSetData, byteParser.getUint8());
    }

    public static LapDataMessage lapDataMessage(PacketHeader header, ByteBufferParser byteParser) {
        var lapData = new LapDataMessage.LapData[22];
        for (int i = 0; i < 22; ++i) {
            lapData[i] =
                    new LapDataMessage.LapData(
                            byteParser.getUint32(),
                            byteParser.getUint32(),
                            byteParser.getUint16(),
                            byteParser.getUint8(),
                            byteParser.getUint16(),
                            byteParser.getUint8(),
                            byteParser.getInt16(),
                            byteParser.getInt16(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint16(),
                            byteParser.getUint16(),
                            byteParser.getUint8());
        }
        return new LapDataMessage(header, lapData, byteParser.getUint8(), byteParser.getUint8());
    }

    public static CarStatusMessage carStatusMessage(PacketHeader header, ByteBufferParser byteParser) {
        CarStatusMessage.CarStatusData[] data = new CarStatusMessage.CarStatusData[22];
        for (int i = 0; i < 22; ++i) {
            data[i] =
                    new CarStatusMessage.CarStatusData(
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getUint16(),
                            byteParser.getUint16(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint16(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getUint8(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getUint8(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getFloat(),
                            byteParser.getUint8());
        }
        return new CarStatusMessage(header, data);
    }

    public static CarTelemetryMessage carTelemetryMessage(PacketHeader header, ByteBufferParser byteParser) {
        CarTelemetryMessage.CarTelemetryData[] data =
                new CarTelemetryMessage.CarTelemetryData[22];
        for (int i = 0; i < 22; ++i) {
            data[i] = new CarTelemetryMessage.CarTelemetryData(
                    byteParser.getUint16(),
                    byteParser.getFloat(),
                    byteParser.getFloat(),
                    byteParser.getFloat(),
                    byteParser.getUint8(),
                    byteParser.getUint8(),
                    byteParser.getUint16(),
                    byteParser.getUint8(),
                    byteParser.getUint8(),
                    byteParser.getUint16(),
                    byteParser.getUint16Array(4),
                    byteParser.getUint8Array(4),
                    byteParser.getUint8Array(4),
                    byteParser.getUint16(),
                    byteParser.getFloatArray(4),
                    byteParser.getUint8Array(4)
            );
        }

        return new CarTelemetryMessage(header,
                data, byteParser.getUint8(), byteParser.getUint8(), byteParser.getUint8());
    }

    public static GameMessage motionExtendedMessage(PacketHeader header, ByteBufferParser byteParser) {
        byteParser.advance(217 - headerSize);
        return null;
    }

    public static GameMessage sessionMessage(PacketHeader header, ByteBufferParser byteParser) {
        byteParser.advance(644 - headerSize);
        return null;
    }

    public static GameMessage eventMessage(PacketHeader header, ByteBufferParser byteParser) {
        byteParser.advance(45 - headerSize);
        return null;
    }

    public static GameMessage participantsMessage(PacketHeader header, ByteBufferParser byteParser) {
        byteParser.advance(1306 - headerSize);
        return null;
    }

    public static GameMessage carSetups(PacketHeader header, ByteBufferParser byteParser) {
        byteParser.advance(1107 - headerSize);
        return null;
    }

    public static GameMessage finalClassification(PacketHeader header, ByteBufferParser byteParser) {
        byteParser.advance(1020 - headerSize);
        return null;
    }

    public static GameMessage lobbyInfo(PacketHeader header, ByteBufferParser byteParser) {
        byteParser.advance(1218 - headerSize);
        return null;
    }
}
