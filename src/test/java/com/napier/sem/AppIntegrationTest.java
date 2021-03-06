package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.util.Assert;

import javax.validation.constraints.Null;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060");
    }

    //Integration test for Report on Population of All Countries in World
    //Added by D.C. Lake April 03 2021
    @Test
    void CountryPopulationReportIntegrationTest() {
        ArrayList<Country> countries = app.getCountryByPopulation();

        assertEquals(232, countries.size());

        countries.forEach(Country -> {
            assertNotNull(Country.country_code);
            assertNotNull(Country.country_name);
            assertNotNull(Country.continent);
            assertNotNull(Country.Region);
            assertNotEquals(-1, Country.Population);
            // assertTrue(Country.Capital instanceof City);
        });
    }

    @Test
    void getCountryByContinetIntegrationTest() {
        ArrayList<Country> countries = app.getCountryByContinent("Africa");

        assertEquals(57, countries.size());

        countries.forEach(Country -> {
            assertNotNull(Country.country_code);
            assertNotNull(Country.country_name);
            assertNotNull(Country.continent);
            assertNotNull(Country.Region);
            assertNotEquals(-1, Country.Population);
            // assertTrue(Country.Capital instanceof City);
        });

    }
    @Test
    public void someTest() {
        try {
           ArrayList <Country> countries= app.getCountryByPopulation();
        }
        catch (Exception e) {
           // throw Exception;
            Assert.isNull(app.getCountryByPopulation());
           // Assert.doesNotContain("Exception " + e);
        }
    }
    


    @Test
    void getTopNCountryByContinentIntegrationTest() {
        ArrayList<Country> countries = app.getTopNCountryByContinent("Africa", 4);

        assertEquals(4, countries.size());

        countries.forEach(Country -> {
            assertNotNull(Country.country_code);
            assertNotNull(Country.country_name);
            assertNotNull(Country.continent);
            assertNotNull(Country.Region);
            assertNotEquals(-1, Country.Population);
            // assertTrue(Country.Capital instanceof City);
        });
    }
    @Test
    void getCountryByRegionIntegrationTest() {
        ArrayList<Country> countries = app.getCountryByRegion("Caribbean");

        assertEquals(24, countries.size());

        countries.forEach(Country -> {
            assertNotNull(Country.country_code);
            assertNotNull(Country.country_name);
            assertNotNull(Country.continent);
            assertNotNull(Country.Region);
            assertNotEquals(-1, Country.Population);
            // assertTrue(Country.Capital instanceof City);
        });
    }
    @Test
    void getTopNCountryByRegionIntegrationTest() {
        ArrayList<Country> countries = app.getTopNCountryByRegion("Caribbean",4);

        assertEquals(4, countries.size());

        countries.forEach(Country -> {
            assertNotNull(Country.country_code);
            assertNotNull(Country.country_name);
            assertNotNull(Country.continent);
            assertNotNull(Country.Region);
            assertNotEquals(-1, Country.Population);
            // assertTrue(Country.Capital instanceof City);
        });
    }
    @Test
    void getTopNCountryByPopulationIntegrationTest() {
        ArrayList<Country> countries = app.getTopNCountryByPopulation(4);

        assertEquals(4, countries.size());

        countries.forEach(Country -> {
            assertNotNull(Country.country_code);
            assertNotNull(Country.country_name);
            assertNotNull(Country.continent);
            assertNotNull(Country.Region);
            assertNotEquals(-1, Country.Population);
            // assertTrue(Country.Capital instanceof City);
        });
    }
    @Test
    void getWorldCitiesIntegrationTest() {
        ArrayList<City> cities = app.getWorldCities();

        assertEquals(4079, cities.size());

        cities.forEach(City -> {
            assertNotNull(City.getCity_name());
            assertNotNull(City.getCountry_name());
            assertNotNull(City.getDistrict());
            assertNotNull(City.getPopulation());
        });
    }
    @Test
    void getCountriesCitiesIntegrationTest() {
        ArrayList<City> cities = app.getCountriesCities("India");

        assertEquals(341, cities.size());

        cities.forEach(City -> {
            assertNotNull(City.getCity_name());
            assertNotNull(City.getCountry_name());
            assertNotNull(City.getDistrict());
            assertNotNull(City.getPopulation());
        });
    }
    @Test
    void getContinentCitiesIntegrationTest() {
        ArrayList<City> cities = app.getContinentCities("Africa");

        assertEquals(366, cities.size());

        cities.forEach(City -> {
            assertNotNull(City.getCity_name());
            assertNotNull(City.getCountry_name());
            assertNotNull(City.getDistrict());
            assertNotNull(City.getPopulation());
        });
    }
    @Test
    void getRegionCitiesIntegrationTest() {
        ArrayList<City> cities = app.getRegionCities("Caribbean");

        assertEquals(58, cities.size());

        cities.forEach(City -> {
            assertNotNull(City.getCity_name());
            assertNotNull(City.getCountry_name());
            assertNotNull(City.getDistrict());
            assertNotNull(City.getPopulation());
        });
    }
    @Test
    void getDistrictCitiesIntegrationTest() {
        ArrayList<City> cities = app.getDistrictCities("New York");

        assertEquals(6, cities.size());

        cities.forEach(City -> {
            assertNotNull(City.getCity_name());
            assertNotNull(City.getCountry_name());
            assertNotNull(City.getDistrict());
            assertNotNull(City.getPopulation());
        });
    }
    @Test
    void getTopWorldCitiesIntegrationTest() {
        ArrayList<City> cities = app.getTopNWorldCities(5);

        assertEquals(5, cities.size());

        cities.forEach(City -> {
            assertNotNull(City.getCity_name());
            assertNotNull(City.getCountry_name());
            assertNotNull(City.getDistrict());
            assertNotNull(City.getPopulation());
        });
    }
    @Test
    void getTopNCitiesInContinentIntegrationTest() {
        ArrayList<City> cities = app.getTopNCitiesInContinent("North America", 10);

        assertEquals(10, cities.size());

        cities.forEach(City -> {
            assertNotNull(City.getCity_name());
            assertNotNull(City.getCountry_name());
            assertNotNull(City.getDistrict());
            assertNotNull(City.getPopulation());
        });
    }
    @Test
    void getTopNCitiesInRegionIntegrationTest() {
        ArrayList<City> cities = app.getTopNCitiesInRegion("Polynesia", 10);

        assertEquals(10, cities.size());

        cities.forEach(City -> {
            assertNotNull(City.getCity_name());
            assertNotNull(City.getCountry_name());
            assertNotNull(City.getDistrict());
            assertNotNull(City.getPopulation());
        });
    }
    @Test
    void getWorldCapitalCitiesIntegrationTest() {
        ArrayList<CapitalCity> capitalCities = app.getWorldCapitalCities();

        assertEquals(232, capitalCities.size());

        capitalCities.forEach(City -> {
            assertNotNull(City.getName());
            assertNotNull(City.getCountry());
            assertNotNull(City.getPopulation());
        });
    }
    @Test
    void getContinentCapitalCitiesIntegrationTest() {
        ArrayList<CapitalCity> capitalCities = app.getContinentCapitalCities("South America");

        assertEquals(14, capitalCities.size());

        capitalCities.forEach(City -> {
            assertNotNull(City.getName());
            assertNotNull(City.getCountry());
            assertNotNull(City.getPopulation());
        });
    }
    @Test
    void getRegionCapitalCitiesIntegrationTest() {
        ArrayList<CapitalCity> capitalCities = app.getRegionCapitalCities("Caribbean");

        assertEquals(24, capitalCities.size());

        capitalCities.forEach(CapitalCity -> {
            assertNotNull(CapitalCity.getName());
            assertNotNull(CapitalCity.getCountry());
            assertNotNull(CapitalCity.getPopulation());
        });
    }
    @Test
    void TopNContinentCapitalCitiesIntegrationTest() {
        ArrayList<CapitalCity> capitalCities = app.getTopNContinentCapitalCities("Africa", 6);

        assertEquals(6, capitalCities.size());

        capitalCities.forEach(CapitalCity -> {
            assertNotNull(CapitalCity.getName());
            assertNotNull(CapitalCity.getCountry());
            assertNotNull(CapitalCity.getPopulation());
        });
    }
    @Test
    void TopNCapitalCitiesRegionIntegrationTest() {
        ArrayList<City> cities = app.getTopNCapitalCitiesRegion("Western Africa",6);

        assertEquals(6, cities.size());

        cities.forEach(City -> {
            assertNotNull(City.getCity_name());
            assertNotNull(City.getCountry_name());
            assertNotNull(City.getDistrict());
            assertNotNull(City.getPopulation());
        });
    }
    @Test
    void CityDwellersContinent() {
        ArrayList<CityDwellers> entries = app.getCityDwellersContinent();

        assertEquals(7, entries.size());

        entries.forEach(Entry -> {
            assertNotNull(Entry.getName());
            assertNotNull(Entry.getTotalPopulation());
            assertNotNull(Entry.getCityDwellers());
            assertNotNull(Entry.getPercentageCityDwellers());
            assertNotNull(Entry.getNonCityDwellers());
            assertNotNull(Entry.getPercentageNonCityDwellers());
        });
    }
    @Test
    void CityDwellersRegion() {
        ArrayList<CityDwellers> entries = app.getCityDwellersRegion();

        assertEquals(25, entries.size());

        entries.forEach(Entry -> {
            assertNotNull(Entry.getName());
            assertNotNull(Entry.getTotalPopulation());
            assertNotNull(Entry.getCityDwellers());
            assertNotNull(Entry.getPercentageCityDwellers());
            assertNotNull(Entry.getNonCityDwellers());
            assertNotNull(Entry.getPercentageNonCityDwellers());
        });
    }
    @Test
    void CityDwellersCountry() {
        ArrayList<CityDwellers> entries = app.getCityDwellersCountry();

        assertEquals(239, entries.size());

        entries.forEach(Entry -> {
            assertNotNull(Entry.getName());
            assertNotNull(Entry.getTotalPopulation());
            assertNotNull(Entry.getCityDwellers());
            assertNotNull(Entry.getPercentageCityDwellers());
            assertNotNull(Entry.getNonCityDwellers());
            assertNotNull(Entry.getPercentageNonCityDwellers());
        });
    }
    @Test
    void Languages() {
        ArrayList<Language> languages = app.getLanguages();

        assertEquals(5, languages.size());

        languages.forEach(Language -> {
            assertNotNull(Language.getLanguage());
            assertNotNull(Language.getTotalSpeakers());
            assertNotNull(Language.getWorldPercentage());
        });
    }
        @AfterAll
        static void Disconnect ()
        {
            app.disconnect();
        }
    }
