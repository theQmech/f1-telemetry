package xyz.rganvir.f1telemetry.messages;

public record LapDataMessage(LapData[] lapdata, int timeTrialPBCarIdx, int timeTrialRivalCarIdx)
        implements GameMessage {
    record LapData(
            long lastLapTimeInMS, // Last lap time in milliseconds
            long currentLapTimeInMS, // Current time around the lap in milliseconds
            int sector1TimeInMS, // Sector 1 time in milliseconds
            int sector1TimeMinutes, // Sector 1 whole minute part
            int sector2TimeInMS, // Sector 2 time in milliseconds
            int sector2TimeMinutes, // Sector 2 whole minute part
            int deltaToCarInFrontInMS, // Time delta to car in front in milliseconds
            int deltaToRaceLeaderInMS, // Time delta to race leader in milliseconds
            float lapDistance, // Distance vehicle is around current lap in metres – could
            // be negative if line hasn’t been crossed yet
            float totalDistance, // Total distance travelled in session in metres – could
            // be negative if line hasn’t been crossed yet
            float safetyCarDelta, // Delta in seconds for safety car
            int carPosition, // Car race position
            int currentLapNum, // Current lap number
            int pitStatus, // 0 = none, 1 = pitting, 2 = in pit area
            int numPitStops, // Number of pit stops taken in this race
            int sector, // 0 = sector1, 1 = sector2, 2 = sector3
            int currentLapInvalid, // Current lap invalid - 0 = valid, 1 = invalid
            int penalties, // Accumulated time penalties in seconds to be added
            int totalWarnings, // Accumulated number of warnings issued
            int cornerCuttingWarnings, // Accumulated number of corner cutting warnings issued
            int numUnservedDriveThroughPens, // Num drive through pens left to serve
            int numUnservedStopGoPens, // Num stop go pens left to serve
            int gridPosition, // Grid position the vehicle started the race in
            int driverStatus, // Status of driver - 0 = in garage, 1 = flying lap
            // 2 = in lap, 3 = out lap, 4 = on track
            int resultStatus, // Result status - 0 = invalid, 1 = inactive, 2 = active
            // 3 = finished, 4 = didnotfinish, 5 = disqualified
            // 6 = not classified, 7 = retired
            int pitLaneTimerActive, // Pit lane timing, 0 = inactive, 1 = active
            int pitLaneTimeInLaneInMS, // If active, the current time spent in the pit lane in ms
            int pitStopTimerInMS, // Time of the actual pit stop in ms
            int pitStopShouldServePen // Whether the car should serve a penalty at this stop
            ) {}
}
