package com.napier.sem;

public class Language {
    private String Language;
    private long TotalSpeakers;
    private float WorldPercentage;

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public long getTotalSpeakers() {
        return TotalSpeakers;
    }

    public void setTotalSpeakers(long totalSpeakers) {
        TotalSpeakers = totalSpeakers;
    }

    public float getWorldPercentage() {
        return WorldPercentage;
    }

    public void setWorldPercentage(float worldPercentage) {
        WorldPercentage = worldPercentage;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-15s" ,
               Language, TotalSpeakers, WorldPercentage );
    }
}

