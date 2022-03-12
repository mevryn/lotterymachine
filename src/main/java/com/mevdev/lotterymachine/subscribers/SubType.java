package com.mevdev.lotterymachine.subscribers;

public enum SubType {
    GIFTED, NORMAL, PRIME;


    public static SubType returnSubType(String subType) {
        return subType.equals("gift") ? GIFTED : NORMAL;
    }
}
