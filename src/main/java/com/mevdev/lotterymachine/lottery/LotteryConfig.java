package com.mevdev.lotterymachine.lottery;

public class LotteryConfig {
    private final int giftedTier1Tokens;
    private final int giftedTier2Tokens;
    private final int giftedTier3Tokens;
    private final int tier1Tokens;
    private final int tier2Tokens;
    private final int tier3Tokens;
    private final int primeTokens;

    public LotteryConfig(int giftedTier1Tokens, int giftedTier2Tokens, int giftedTier3Tokens, int tier1Tokens, int tier2Tokens, int tier3Tokens, int primeTokens) {
        this.giftedTier1Tokens = giftedTier1Tokens;
        this.giftedTier2Tokens = giftedTier2Tokens;
        this.giftedTier3Tokens = giftedTier3Tokens;
        this.tier1Tokens = tier1Tokens;
        this.tier2Tokens = tier2Tokens;
        this.tier3Tokens = tier3Tokens;
        this.primeTokens = primeTokens;
    }

    public int getGiftedTier1Tokens() {
        return giftedTier1Tokens;
    }

    public int getTier1Tokens() {
        return tier1Tokens;
    }

    public int getTier2Tokens() {
        return tier2Tokens;
    }

    public int getTier3Tokens() {
        return tier3Tokens;
    }

    public int getPrimeTokens() {
        return primeTokens;
    }

    public int getGiftedTier2Tokens() {
        return giftedTier2Tokens;
    }

    public int getGiftedTier3Tokens() {
        return giftedTier3Tokens;
    }
}

