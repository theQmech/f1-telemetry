package xyz.rganvir.f1telemetry.messages;

public record PacketHeader(
    int packetFormat, // 2023
    int gameYear, // Game year - last two digits e.g. 23
    int gameMajorVersion, // Game major version - "X.00"
    int gameMinorVersion, // Game minor version - "1.XX"
    int packetVersion, // Version of this packet type, all start from 1
    int packetId, // Identifier for the packet type, see below
    long sessionUID, // Unique identifier for the session
    float sessionTime, // Session timestamp
    long frameIdentifier, // Identifier for the frame the data was retrieved on
    long overallFrameIdentifier, // Overall identifier for the frame the
    // data was retrieved
    // on, doesn't go back after flashbacks
    int playerCarIndex, // Index of player's car in the array
    int secondaryPlayerCarIndex // Index of secondary player's car in the
    // array (splitscreen)
    // 255 if no second player
    ) {}
