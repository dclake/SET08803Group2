package com.napier.sem;

public class CityDwellers {
    private String Name;
    private long TotalPopulation;
    private int CityDwellers;
    private float PercentageCityDwellers;
    private long NonCityDwellers;
    private float PercentageNonCityDwellers;



    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
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

    public float getPercentageCityDwellers() {
        return PercentageCityDwellers;
    }

    public void setPercentageCityDwellers(float percentageCityDwellers) {
        PercentageCityDwellers = percentageCityDwellers;
    }

    public long getNonCityDwellers() {
        return NonCityDwellers;
    }

    public void setNonCityDwellers(long nonCityDwellers) {
        NonCityDwellers = nonCityDwellers;
    }

    public float getPercentageNonCityDwellers() {
        return PercentageNonCityDwellers;
    }

    public void setPercentageNonCityDwellers(float percentageNonCityDwellers) {
        PercentageNonCityDwellers = percentageNonCityDwellers;
    }
    @Override
    public String toString() {
        return String.format("%-45s %-20s %-15s %-25s %-15s %-15s" ,
                Name, TotalPopulation, CityDwellers, PercentageCityDwellers, NonCityDwellers, PercentageNonCityDwellers );
    }
}

