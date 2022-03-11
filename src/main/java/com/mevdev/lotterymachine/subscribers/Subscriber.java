package com.mevdev.lotterymachine.subscribers;

public class Subscriber {

    private final String name;
    private final Tier tier;
    private final SubType type;

    public Subscriber(String name, Tier tier, SubType type) {
        this.name = name;
        this.tier = tier;
        this.type = type;
    }

    public static int getNameIndexInCSV() {
        return 0;
    }


    public String getName() {
        return name;
    }

    public Tier getTier() {
        return tier;
    }

    public SubType getType() {
        return type;
    }

    public static int getTierIndexInCSV() {
        return 2;
    }

    public static int getTypeIndexInCSV() {
        return 5;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "name='" + name + '\'' +
                ", tier=" + tier +
                ", type=" + type +
                '}';
    }
}
