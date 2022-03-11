package com.mevdev.lotterymachine.subscribers;

public enum Tier {
    FIRST("Tier 1"), SECOND("Tier 2"), THIRD("Tier 3");

    private final String tierString;

    Tier(String tierString) {
        this.tierString = tierString;
    }

    public static Tier getTier(String value) {
        for (Tier tier : Tier.values()) {
            if (tier.tierString.equals(value)) {
                return tier;
            }
        }
        return null;
    }
}
