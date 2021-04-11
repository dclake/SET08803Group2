package com.napier.sem;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class City {
    /**
     * City Name
     */
    private String city_name;
    /**
     * City Name
     */
    private String country_name;
    /**
     * District
     */
    private String district;
    /**
     * Population
     */
    private int Population;


    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        Population = population;
    }

    @Override
    public String toString() {
        return String.format("%-35s %-40s %-35s %-15s",
                city_name, country_name,district, Population);
    }


}

