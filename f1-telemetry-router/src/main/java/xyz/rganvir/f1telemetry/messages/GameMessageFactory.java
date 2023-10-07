package xyz.rganvir.f1telemetry.messages;

public enum GameMessageFactory {
    ;

    public static CarDamageMessage carDamagePacket(ByteBufferParser buffer) {
        CarDamageMessage.CarDamageData[] data = new CarDamageMessage.CarDamageData[22];
        for (int i = 0; i < 22; ++i) {
            data[i] =
                    new CarDamageMessage.CarDamageData(
                            buffer.getFloatArray(4),
                            buffer.getUint8Array(4),
                            buffer.getUint8Array(4),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8(),
                            buffer.getUint8());
        }
        return new CarDamageMessage(data);
    }

    public static MotionMessage motionMessage(ByteBufferParser buffer) {
        var data = new MotionMessage.CarMotionData[22];
        for (int i = 0; i < 22; i++) {
            data[i] =
                    new MotionMessage.CarMotionData(
                            buffer.getFloat(),
                            buffer.getFloat(),
                            buffer.getFloat(),
                            buffer.getFloat(),
                            buffer.getFloat(),
                            buffer.getFloat(),
                            buffer.getInt16(),
                            buffer.getInt16(),
                            buffer.getInt16(),
                            buffer.getInt16(),
                            buffer.getInt16(),
                            buffer.getInt16(),
                            buffer.getFloat(),
                            buffer.getFloat(),
                            buffer.getFloat(),
                            buffer.getFloat(),
                            buffer.getFloat(),
                            buffer.getFloat());
        }
        return new MotionMessage(data);
    }

    public static SessionHistoryMessage sessionHistoryMessage(ByteBufferParser buffer) {
        var lapHistoryData = new SessionHistoryMessage.LapHistoryData[100];
        for (int i = 0; i < 100; ++i) {
            lapHistoryData[i] =
                    new SessionHistoryMessage.LapHistoryData(
                            buffer.getUint32(),
                            buffer.getUint16(),
                            buffer.getUint8(),
                            buffer.getUint16(),
                            buffer.getUint8(),
                            buffer.getUint16(),
                            buffer.getUint8(),
                            buffer.getUint8());
        }
        var tyreStintHistoryData = new SessionHistoryMessage.TyreStintHistoryData[8];
        for (int i = 0; i < 8; ++i) {
            tyreStintHistoryData[i] =
                    new SessionHistoryMessage.TyreStintHistoryData(
                            buffer.getUint8(), buffer.getUint8(), buffer.getUint8());
        }
        return new SessionHistoryMessage(
                buffer.getUint8(),
                buffer.getUint8(),
                buffer.getUint8(),
                buffer.getUint8(),
                buffer.getUint8(),
                buffer.getUint8(),
                buffer.getUint8(),
                lapHistoryData,
                tyreStintHistoryData);
    }

    public static TyreSetsMessage tyreSetsMessage(ByteBufferParser byteParser) {
        var tyreSetData = new TyreSetsMessage.TyreSetData[20];
        for (int i = 0; i < 20; ++i) {
            tyreSetData[i] =
                    new TyreSetsMessage.TyreSetData(
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

    public static LapDataMessage lapDataMessage(ByteBufferParser byteParser) {
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
        return new LapDataMessage(lapData, byteParser.getUint8(), byteParser.getUint8());
    }

    public static CarStatusMessage carStatusMessage(ByteBufferParser byteParser) {
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
        return new CarStatusMessage(data);
    }

    public static CarTelemetryMessage carTelemetryMessage(ByteBufferParser byteParser) {
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
                    byteParser.getUint16Array(4),
                    byteParser.getUint16Array(4),
                    byteParser.getUint16(),
                    byteParser.getFloatArray(4),
                    byteParser.getUint8Array(4)
            );
        }

        return new CarTelemetryMessage(
                data, byteParser.getUint8(), byteParser.getUint8(), byteParser.getUint8());
    }
}
