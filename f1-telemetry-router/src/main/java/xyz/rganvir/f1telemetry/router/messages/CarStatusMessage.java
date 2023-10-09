package xyz.rganvir.f1telemetry.router.messages;

public record CarStatusMessage(CarStatusData[] carStatusData) implements GameMessage {
    record CarStatusData(
            int tractionControl, // Traction control - 0 = off, 1 = medium, 2 = full
            int antiLockBrakes, // 0 (off) - 1 (on)
            int fuelMix, // Fuel mix - 0 = lean, 1 = standard, 2 = rich, 3 = max
            int frontBrakeBias, // Front brake bias (percentage)
            int pitLimiterStatus, // Pit limiter status - 0 = off, 1 = on
            float fuelInTank, // Current fuel mass
            float fuelCapacity, // Fuel capacity
            float fuelRemainingLaps, // Fuel remaining in terms of laps (value on MFD)
            int maxRPM, // Cars max RPM, point of rev limiter
            int idleRPM, // Cars idle RPM
            int maxGears, // Maximum number of gears
            int drsAllowed, // 0 = not allowed, 1 = allowed
            int drsActivationDistance, // 0 = DRS not available, non-zero - DRS will be available
            // in [X] metres
            int actualTyreCompound, // F1 Modern - 16 = C5, 17 = C4, 18 = C3, 19 = C2, 20 = C1
            // 21 = C0, 7 = inter, 8 = wet
            // F1 Classic - 9 = dry, 10 = wet
            // F2 – 11 = super soft, 12 = soft, 13 = medium, 14 = hard
            // 15 = wet
            int visualTyreCompound, // F1 visual (can be different from actual compound)
            // 16 = soft, 17 = medium, 18 = hard, 7 = inter, 8 = wet
            // F1 Classic – same as above
            // F2 ‘19, 15 = wet, 19 – super soft, 20 = soft
            // 21 = medium , 22 = hard
            int tyresAgeLaps, // Age in laps of the current set of tyres
            int vehicleFiaFlags, // -1 = invalid/unknown, 0 = none, 1 = green
            // 2 = blue, 3 = yellow
            float enginePowerICE, // Engine power output of ICE (W)
            float enginePowerMGUK, // Engine power output of MGU-K (W)
            float ersStoreEnergy, // ERS energy store in Joules
            int ersDeployMode, // ERS deployment mode, 0 = none, 1 = medium
            // 2 = hotlap, 3 = overtake
            float ersHarvestedThisLapMGUK, // ERS energy harvested this lap by MGU-K
            float ersHarvestedThisLapMGUH, // ERS energy harvested this lap by MGU-H
            float ersDeployedThisLap, // ERS energy deployed this lap
            int networkPaused // Whether the car is paused in a network game)
            ) {}
}
