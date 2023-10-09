package xyz.rganvir.f1telemetry.router.messages;

public record CarTelemetryMessage(
        CarTelemetryData[] carTelemetryData,
        int mfdPanelIndex,
        int mfdPanelIndexSecondPlayer,
        int suggestedGear)
        implements GameMessage {
    record CarTelemetryData(
            int speed, // Speed of car in kilometres per hour
            float throttle, // Amount of throttle applied (0.0 to 1.0)
            float steer, // Steering (-1.0 (full lock left) to 1.0 (full lock right))
            float brake, // Amount of brake applied (0.0 to 1.0)
            int clutch, // Amount of clutch applied (0 to 100)
            int gear, // Gear selected (1-8, N=0, R=-1)
            int engineRPM, // Engine RPM
            int drs, // 0 = off, 1 = on
            int revLightsPercent, // Rev lights indicator (percentage)
            int revLightsBitValue, // Rev lights (bit 0 = leftmost LED, bit 14 = rightmost LED)
            int[] brakesTemperature, // Brakes temperature (celsius)
            int[] tyresSurfaceTemperature, // Tyres surface temperature (celsius)
            int[] tyresInnerTemperature, // Tyres inner temperature (celsius)
            int engineTemperature, // Engine temperature (celsius)
            float[] tyresPressure, // Tyres pressure (PSI)
            int[] surfaceType // Driving surface, see appendices
            ) {}
}
