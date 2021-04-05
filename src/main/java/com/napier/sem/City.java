package com.napier.sem;

import java.sql.Connection;
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

    public  static ArrayList<City> getWorldCities()
    {
        try
        {
            // Create an SQL statement
            //Connection con = null;
            //Statement stmt = con.createStatement();
            Statement stmt = App.getCon().createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n"
                            + "FROM world.city , world.country\n"
                            + "where country.Code = city.CountryCode\n"
                            + "ORDER BY Population desc;";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.city_name = rset.getString("city.Name");
                city.country_name = rset.getString("country.name");
                city.district = rset.getString("city.District");
                city.Population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }
    public  static ArrayList<City> getContinentCities(String Continent)
    {
        try
        {
            // Create an SQL statement
            //Connection con = null;
            //Statement stmt = con.createStatement();
            Statement stmt = App.getCon().createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.CountryCode = country.Code\n" +
                            "where country.Continent like '" + Continent + "'\n" +
                            "ORDER BY Population desc";

            System.out.println(strSelect);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.city_name = rset.getString("city.Name");
                city.country_name = rset.getString("country.name");
                city.district = rset.getString("city.District");
                city.Population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }
    public  static ArrayList<City> getRegionCities(String Region)
    {
        try
        {
            // Create an SQL statement
            //Connection con = null;
            //Statement stmt = con.createStatement();
            Statement stmt = App.getCon().createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.CountryCode = country.Code\n" +
                            "where country.Region like '" + Region + "'\n" +
                            "ORDER BY Population desc";

            System.out.println(strSelect);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.city_name = rset.getString("city.Name");
                city.country_name = rset.getString("country.name");
                city.district = rset.getString("city.District");
                city.Population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }
    public  static ArrayList<City> getCountriesCities(String Country)
    {
        try
        {
            // Create an SQL statement
            //Connection con = null;
            //Statement stmt = con.createStatement();
            Statement stmt = App.getCon().createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population \n" +
                            "FROM world.city \n" +
                            "Join world.country on city.CountryCode = country.Code\n" +
                            "where country.Name like '" + Country + "'\n" +
                            "ORDER BY Population desc";

            System.out.println(strSelect);
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.city_name = rset.getString("city.Name");
                city.country_name = rset.getString("country.name");
                city.district = rset.getString("city.District");
                city.Population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }
    public static void printCities(ArrayList<City> cities)
    {
        // Check countries is not null
        if (cities == null) {
            System.out.println("No Cities");
            return;
        }
        // Print header
        System.out.println("___________________________________________________________________________________________________________________________________________________");
        System.out.println(String.format("%-35s %-40s %-35s %-15s", "City", "Country", "District", "Population"));
        System.out.println("___________________________________________________________________________________________________________________________________________________");
        // Loop over all countries in the list
        for (City city : cities)
        {
            if (city == null)
                continue;
            String cities_string =
                    String.format("%-35s %-40s %-35s %-15s",
                            city.city_name, city.country_name, city.district, city.Population);
            System.out.println(cities_string);
        }
    }
}

