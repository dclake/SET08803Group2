package com.napier.sem;

public class CapitalCity {
    private String Name;
    private String Country;
    private long Population;

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
     * @param name the name
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return Country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        Country = country;
    }

    /**
     * Gets population.
     *
     * @return the population
     */
    public long getPopulation() {
        return Population;
    }

    /**
     * Sets population.
     *
     * @param population the population
     */
    public void setPopulation(long population) {
        Population = population;
    }
    @Override
    public String toString() {
        return String.format("%-35s %-40s %-15s",
                Name, Country, Population);
    }




}
