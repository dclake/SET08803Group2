package com.napier.sem;

/**
 * The type Language.
 */
public class Language {
    private String Language;
    private long TotalSpeakers;
    private float WorldPercentage;

    /**
     * Gets language.
     *
     * @return the language
     */
    public String getLanguage() {
        return Language;
    }

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(String language) {
        Language = language;
    }

    /**
     * Gets total speakers.
     *
     * @return the total speakers
     */
    public long getTotalSpeakers() {
        return TotalSpeakers;
    }

    /**
     * Sets total speakers.
     *
     * @param totalSpeakers the total speakers
     */
    public void setTotalSpeakers(long totalSpeakers) {
        TotalSpeakers = totalSpeakers;
    }

    /**
     * Gets world percentage.
     *
     * @return the world percentage
     */
    public float getWorldPercentage() {
        return WorldPercentage;
    }

    /**
     * Sets world percentage.
     *
     * @param worldPercentage the world percentage
     */
    public void setWorldPercentage(float worldPercentage) {
        WorldPercentage = worldPercentage;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-15s" ,
               Language, TotalSpeakers, WorldPercentage );
    }
}

