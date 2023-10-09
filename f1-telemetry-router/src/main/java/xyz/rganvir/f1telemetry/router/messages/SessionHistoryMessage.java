package xyz.rganvir.f1telemetry.router.messages;

public record SessionHistoryMessage(
        int carIdx, // Index of the car this lap data relates to
        int numLaps, // Num laps in the data (including current partial lap)
        int numTyreStints, // Number of tyre stints in the data
        int bestLapTimeLapNum, // Lap the best lap time was achieved on
        int bestSector1LapNum, // Lap the best Sector 1 time was achieved on
        int bestSector2LapNum, // Lap the best Sector 2 time was achieved on
        int bestSector3LapNum, // Lap the best Sector 3 time was achieved on
        LapHistoryData[] lapHistoryData, // 100 laps of data max
        TyreStintHistoryData[] tyreStintsHistoryData)
        implements GameMessage {
    @Override
    public MessageType type() {
        return MessageType.SESSION_HISTORY;
    }

    record LapHistoryData(
            long lapTimeInMS, // Lap time in milliseconds
            int sector1TimeInMS, // Sector 1 time in milliseconds
            int sector1TimeMinutes, // Sector 1 whole minute part
            int sector2TimeInMS, // Sector 2 time in milliseconds
            int sector2TimeMinutes, // Sector 2 whole minute part
            int sector3TimeInMS, // Sector 3 time in milliseconds
            int sector3TimeMinutes, // Sector 3 whole minute part
            int lapValidBitFlags // 0x01 bit set-lap valid,      0x02 bit set-sector 1 valid
            // 0x04 bit set-sector 2 valid, 0x08 bit set-sector 3 valid
            ) {}

    record TyreStintHistoryData(
            int endLap, // Lap the tyre usage ends on (255 of current tyre)
            int tyreActualCompound, // Actual tyres used by this driver
            int tyreVisualCompound // Visual tyres used by this driver
            ) {}
}
