package com.mevdev.lotterymachine.subscribers;

public enum SubType {
    GIFTED, NORMAL, PRIME;


    public static SubType returnSubType(String subType) {
        return switch (subType) {
            case "gift" -> GIFTED;
            case "prime" -> PRIME;
            default -> NORMAL;
        };
    }
}
