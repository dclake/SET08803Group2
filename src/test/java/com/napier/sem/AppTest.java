package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printCountriesTestNull()
    {
        app.printCountries(null);
    }
    @Test
    void printCitiesTestNull()
    {
        app.printCities(null);
    }
    @Test
    void printCityDwellersTestNull()
    {
        app.printCityDwellers(null);
    }
    @Test
    void printLanguageTestNull()
    {
        app.printLanguages(null);
    }
    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        app.printCountries(countries);
    }
    @Test
    void printCitiesTestEmpty()
    {
        ArrayList<City> cities = new ArrayList<City>();
        app.printCities(cities);
    }
    @Test
    void printCityDwellersTestEmpty()
    {
        ArrayList<CityDwellers> entires = new ArrayList<CityDwellers>();
        app.printCityDwellers(entires);
    }
    @Test
    void printLanguageEmpty()
    {
        ArrayList<Language> languages = new ArrayList<Language>();
        app.printLanguages(languages);
    }
    @Test
    void printCountriesTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        app.printCountries(countries);
    }
    @Test
    void printCitiesTestContainsNull() {
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        app.printCities(cities);
    }
    @Test
    void printCityDwellersTestContainsNull() {
        ArrayList<CityDwellers> entries = new ArrayList<CityDwellers>();
        entries.add(null);
        app.printCityDwellers(entries);
    }
    @Test
    void printLanguagesContainsNull() {
        ArrayList<Language> languages = new ArrayList<Language>();
        languages.add(null);
        app.printLanguages(languages);
    }
    @Test
    void printCountries()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.country_code = "CUB";
        country.country_name = "Cuba";
        country.continent = "North America";
        country.Population = 11201000;
        country.Capital = "La Habana";
        countries.add(country);
        app.printCountries(countries);
    }
    @Test
    void printCities()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.setCity_name("Seoul");
        city.setCountry_name("South Korea");
        city.setDistrict("Seoul");
        city.setPopulation(9981619);
        app.printCities(cities);
    }
    @Test
    void printCityDwellers()
    {
        ArrayList<CityDwellers> entries = new ArrayList<CityDwellers>();
        CityDwellers entry = new CityDwellers();
        entry.setName("North America");
        entry.setTotalPopulation(482993000);
        entry.setCityDwellers(168250381);
        entry.setPercentageCityDwellers((float) 34.835);
        entry.setNonCityDwellers(314742619);
        entry.setPercentageNonCityDwellers((float) 34.835);
        app.printCityDwellers(entries);
    }
    @Test
    void printLanguages()
    {
        ArrayList<Language> languages = new ArrayList<Language>();
        Language language = new Language();
        language.setLanguage("Arabic");
        language.setTotalSpeakers(552045100);
        language.setWorldPercentage((float)9.0816);
        app.printLanguages(languages);
    }
}