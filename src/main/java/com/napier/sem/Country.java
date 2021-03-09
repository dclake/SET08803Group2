package com.napier.sem;

public class Country {
    /**
     * Country Code
     */
    public String country_code;

    /**
     * Country Name
     */
    public String country_name;

    /**
     * Continent
     */
    public String continent;

    /**
     * Population
     */
    public Integer Population;
    /**
     * Region
     */
    public String Region;
    /**
     * Capital
     */
    public String Capital;

    public Country() {

    }

    public Country(String country_code, String country_name, String continent, Integer population, String region, String capital) {
        this.country_code = country_code;
        this.country_name = country_name;
        this.continent = continent;
        Population = population;
        Region = region;
        Capital = capital;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Integer getPopulation() {
        return Population;
    }

    public void setPopulation(Integer population) {
        Population = population;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getCapital() {
        return Capital;
    }

    public void setCapital(String capital) {
        Capital = capital;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country_code='" + country_code + '\'' +
                ", country_name='" + country_name + '\'' +
                ", continent='" + continent + '\'' +
                ", Population=" + Population +
                ", Region='" + Region + '\'' +
                ", Capital='" + Capital + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Country c = new Country();
        c.setCountry_name("Scotland");
        System.out.println(c);
    }
}
