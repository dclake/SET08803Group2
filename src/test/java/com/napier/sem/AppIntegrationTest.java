package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

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
            ArrayList<Country> countries = app.getCountryByContinet("Africa");

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
    void getTopNCountryByContinetIntegrationTest() {
        ArrayList<Country> countries = app.getTopNCountryByContinet("Africa",4);

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

    }