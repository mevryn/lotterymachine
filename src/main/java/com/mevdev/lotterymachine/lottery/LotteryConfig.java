package com.mevdev.lotterymachine.lottery;

public class LotteryConfig {
    private final int giftedTokens;
    private final int tier1Tokens;
    private final int tier2Tokens;
    private final int tier3Tokens;
    private final int primeTokens;

    public LotteryConfig(int giftedTokens, int tier1Tokens, int tier2Tokens, int tier3Tokens, int primeTokens) {
        this.giftedTokens = giftedTokens;
        this.tier1Tokens = tier1Tokens;
        this.tier2Tokens = tier2Tokens;
        this.tier3Tokens = tier3Tokens;
        this.primeTokens = primeTokens;
    }

    public int getGiftedTokens() {
        return giftedTokens;
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
}

