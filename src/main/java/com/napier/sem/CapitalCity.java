package com.napier.sem;

public class CapitalCity {
    private String Name;
    private String Country;
    private long Population;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public long getPopulation() {
        return Population;
    }

    public void setPopulation(long population) {
        Population = population;
    }
    @Override
    public String toString() {
        return String.format("%-35s %-40s %-15s",
                Name, Country, Population);
    }




}
