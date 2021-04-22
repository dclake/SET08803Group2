package com.napier.sem;

/**
 * The type City dwellers.
 */
public class CityDwellers {
    private String Name;
    private long TotalPopulation;
    private int CityDwellers;
    private float PercentageCityDwellers;
    private long NonCityDwellers;
    private float PercentageNonCityDwellers;


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets name.
     *
     * @param Name the name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * Gets total population.
     *
     * @return the total population
     */
    public long getTotalPopulation() {
        return TotalPopulation;
    }

    /**
     * Sets total population.
     *
     * @param totalPopulation the total population
     */
    public void setTotalPopulation(long totalPopulation) {
        TotalPopulation = totalPopulation;
    }

    /**
     * Gets city dwellers.
     *
     * @return the city dwellers
     */
    public int getCityDwellers() {
        return CityDwellers;
    }

    /**
     * Sets city dwellers.
     *
     * @param cityDwellers the city dwellers
     */
    public void setCityDwellers(int cityDwellers) {
        CityDwellers = cityDwellers;
    }

    /**
     * Gets percentage city dwellers.
     *
     * @return the percentage city dwellers
     */
    public float getPercentageCityDwellers() {
        return PercentageCityDwellers;
    }

    /**
     * Sets percentage city dwellers.
     *
     * @param percentageCityDwellers the percentage city dwellers
     */
    public void setPercentageCityDwellers(float percentageCityDwellers) {
        PercentageCityDwellers = percentageCityDwellers;
    }

    /**
     * Gets non city dwellers.
     *
     * @return the non city dwellers
     */
    public long getNonCityDwellers() {
        return NonCityDwellers;
    }

    /**
     * Sets non city dwellers.
     *
     * @param nonCityDwellers the non city dwellers
     */
    public void setNonCityDwellers(long nonCityDwellers) {
        NonCityDwellers = nonCityDwellers;
    }

    /**
     * Gets percentage non city dwellers.
     *
     * @return the percentage non city dwellers
     */
    public float getPercentageNonCityDwellers() {
        return PercentageNonCityDwellers;
    }

    /**
     * Sets percentage non city dwellers.
     *
     * @param percentageNonCityDwellers the percentage non city dwellers
     */
    public void setPercentageNonCityDwellers(float percentageNonCityDwellers) {
        PercentageNonCityDwellers = percentageNonCityDwellers;
    }
    @Override
    public String toString() {
        return String.format("%-45s %-20s %-15s %-25s %-15s %-15s" ,
                Name, TotalPopulation, CityDwellers, PercentageCityDwellers, NonCityDwellers, PercentageNonCityDwellers );
    }
}

