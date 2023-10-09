package xyz.rganvir.f1telemetry.router.messages;

public record CarDamageMessage(CarDamageData[] carDamageData) implements GameMessage {
    record CarDamageData(
        float[] tyresWear, // Tyre wear (percentage)
        int[] tyresDamage, // Tyre damage (percentage)
        int[] brakesDamage, // Brakes damage (percentage)
        int frontLeftWingDamage, // Front left wing damage (percentage)
        int frontRightWingDamage, // Front right wing damage (percentage)
        int rearWingDamage, // Rear wing damage (percentage)
        int floorDamage, // Floor damage (percentage)
        int diffuserDamage, // Diffuser damage (percentage)
        int sidepodDamage, // Sidepod damage (percentage)
        int drsFault, // Indicator for DRS fault, 0 = OK, 1 = fault
        int ersFault, // Indicator for ERS fault, 0 = OK, 1 = fault
        int gearBoxDamage, // Gear box damage (percentage)
        int engineDamage, // Engine damage (percentage)
        int engineMGUHWear, // Engine wear MGU-H (percentage)
        int engineESWear, // Engine wear ES (percentage)
        int engineCEWear, // Engine wear CE (percentage)
        int engineICEWear, // Engine wear ICE (percentage)
        int engineMGUKWear, // Engine wear MGU-K (percentage)
        int engineTCWear, // Engine wear TC (percentage)
        int engineBlown, // Engine blown, 0 = OK, 1 = fault
        int engineSeized // Engine seized, 0 = OK, 1 = fault
    ) {
    }
}
