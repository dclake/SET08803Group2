package com.napier.sem;

public class CityDwellers {
    private String Continent;
    private long TotalPopulation;
    private int CityDwellers;
    private int PercentageCityDwellers;
    private long NonCityDwellers;
    private int PercentageNonCityDwellers;

    public String getContinent() {
        return Continent;
    }
    public void setContinent(String Continent) {
        this.Continent = Continent;
    }

    public long getTotalPopulation() {
        return TotalPopulation;
    }

    public void setTotalPopulation(long totalPopulation) {
        TotalPopulation = totalPopulation;
    }

    public int getCityDwellers() {
        return CityDwellers;
    }

    public void setCityDwellers(int cityDwellers) {
        CityDwellers = cityDwellers;
    }

    public int getPercentageCityDwellers() {
        return PercentageCityDwellers;
    }

    public void setPercentageCityDwellers(int percentageCityDwellers) {
        PercentageCityDwellers = percentageCityDwellers;
    }

    public long getNonCityDwellers() {
        return NonCityDwellers;
    }

    public void setNonCityDwellers(long nonCityDwellers) {
        NonCityDwellers = nonCityDwellers;
    }

    public int getPercentageNonCityDwellers() {
        return PercentageNonCityDwellers;
    }

    public void setPercentageNonCityDwellers(int percentageNonCityDwellers) {
        PercentageNonCityDwellers = percentageNonCityDwellers;
    }
    @Override
    public String toString() {
        return String.format("%-35s %-15s %-15s %-15s %-15s %-15s" ,
                Continent, TotalPopulation, CityDwellers, PercentageCityDwellers, NonCityDwellers, PercentageCityDwellers );
    }
}

