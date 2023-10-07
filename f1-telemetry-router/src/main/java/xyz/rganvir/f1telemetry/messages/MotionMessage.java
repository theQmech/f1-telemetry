package xyz.rganvir.f1telemetry.messages;

public record MotionMessage(CarMotionData[] carMotionData) implements GameMessage {

    record CarMotionData(
            float worldPositionX, // World space X position - metres
            float worldPositionY, // World space Y position
            float worldPositionZ, // World space Z position
            float worldVelocityX, // Velocity in world space X – metres/s
            float worldVelocityY, // Velocity in world space Y
            float worldVelocityZ, // Velocity in world space Z
            int worldForwardDirX, // World space forward X direction (normalised)
            int worldForwardDirY, // World space forward Y direction (normalised)
            int worldForwardDirZ, // World space forward Z direction (normalised)
            int worldRightDirX, // World space right X direction (normalised)
            int worldRightDirY, // World space right Y direction (normalised)
            int worldRightDirZ, // World space right Z direction (normalised)
            float gForceLateral, // Lateral G-Force component
            float gForceLongitudinal, // Longitudinal G-Force component
            float gForceVertical, // Vertical G-Force component
            float yaw, // Yaw angle in radians
            float pitch, // Pitch angle in radians
            float roll // Roll angle in radians
            ) {}
}
