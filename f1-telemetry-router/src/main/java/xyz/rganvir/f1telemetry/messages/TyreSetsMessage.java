package xyz.rganvir.f1telemetry.messages;

public record TyreSetsMessage(int cardId, TyreSetData[] tyreSetData, int fittedIdx) implements GameMessage{
    record TyreSetData(
            int     actualTyreCompound,    // Actual tyre compound used
            int     visualTyreCompound,    // Visual tyre compound used
            int     wear,                  // Tyre wear (percentage)
            int     available,             // Whether this set is currently available
            int     recommendedSession,    // Recommended session for tyre set
            int     lifeSpan,              // Laps left in this tyre set
            int     usableLife,            // Max number of laps recommended for this compound
            int     lapDeltaTime,          // Lap delta time in milliseconds compared to fitted set
            int     fitted                // Whether the set is fitted or not

    ) {
    }
}
